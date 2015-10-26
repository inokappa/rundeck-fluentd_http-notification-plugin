import com.dtolabs.rundeck.plugins.notification.NotificationPlugin;
import com.dtolabs.rundeck.core.plugins.configuration.StringRenderingConstants;
import com.dtolabs.rundeck.core.plugins.configuration.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode

// See http://docs.fluentd.org/articles/in_http

class DEFAULTS {
    static String FLUENTD_IN_HTTP_URL = "http://localhost:8888/"
    static String FLUENTD_TAG_PREFIX = "rundeck.notification"
}

/**
 * @param execution
 * @param configuration
 */
def triggerEvent(Map execution, Map configuration) {
  //System.err.println("DEBUG: api_key="+configuration.api_key)
  //System.err.println("DEBUG: excutionData="+execution)
  def fluentdTag = configuration.fluentd_tag_prefix + "." + execution.job.name

  def job_data = [
    execution_id: execution.id,
    execution_href: execution.href,
    execution_status: execution.status.toUpperCase(),
    execution_user: execution.user,
    execution_dateStartedUnixtime: execution.dateStartedUnixtime,
    execution_description: execution.description,
    execution_argstring: execution.argstring,
    execution_project: execution.project,
    execution_loglevel: execution.loglevel,
    execution_failedNodeListString: execution.failedNodeListString,
    execution_failedNodeList: execution.failedNodeList,
    execution_succeededNodeListString: execution.succeededNodeListString,
    execution_succeededNodeList: execution.succeededNodeList,
    execution_nodestatus: execution.nodestatus,
    execution_dateEndedUnixtime: execution.dateEndedUnixtime,
    execution_abortedby: execution.abortedby,
    job_id: execution.job.id,
    job_href: execution.job.href,
    job_name: execution.job.name,
    job_group: execution.job.group,
    job_project: execution.job.project,
    job_description: execution.job.description,
    job_averageDuration: execution.job.averageDuration
  ]

  // Send the request.
  def url = new URL(DEFAULTS.FLUENTD_IN_HTTP_URL + fluentdTag)
  def connection = url.openConnection()
  connection.setRequestMethod("POST")
  connection.addRequestProperty("Content-type", "application/json")
  connection.doOutput = true
  def writer = new OutputStreamWriter(connection.outputStream)
  def json = new ObjectMapper()
  writer.write(json.writeValueAsString(job_data))
  writer.flush()
  writer.close()
  connection.connect()

  // process the response.
  def response = connection.content.text
  //System.err.println("DEBUG: response: " + response)
  if (! response == "" ) {
      System.err.println("ERROR: FluentdInHttpNotification plugin status: " + status)
  }
}

/**
 * Main
**/
rundeckPlugin(NotificationPlugin){
    title="Fluentd_Notification"
    description="Create a Trigger event."
    configuration{
        fluentd_in_httpd_endpoiont title:"Fluentd in_http endpoint", description:"", defaultValue:DEFAULTS.FLUENTD_IN_HTTP_URL,required:true
        fluentd_tag_prefix title:"Fluentd tag prefix", description:"", defaultValue:DEFAULTS.FLUENTD_TAG_PREFIX,required:true
    }
    onstart { Map execution, Map configuration ->
        triggerEvent(execution, configuration)
        true
    }
    onfailure { Map execution, Map configuration ->
        triggerEvent(execution, configuration)
        true
    }
    onsuccess { Map execution, Map configuration ->
        triggerEvent(execution, configuration)
        true
    }

}
