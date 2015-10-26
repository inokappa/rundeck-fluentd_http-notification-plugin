## Rundeck Fluentd HTTP Notification Plugin

### Installation

Copy the groovy script to the plugins directory:

```sh
$ sudo cp FluentdInHttpNotification.groovy /var/lib/rundec/libext/
```

### Configuration

![](https://raw.githubusercontent.com/inokappa/rundeck-fluentd_http-notification-plugin/master/doc/images/2015102601.png)

* Fluentd in_http endpoint
* Fluentd tag prefix

### Run

following log outputs.

```
2015-10-26 22:36:56 +0900 rundeck.notification.hello-world: {"execution_id":2672,"execution_href":"http://xxx.xxx.xx.xx:4440/project/hello-world/execution/follow/2672","execution_status":"FAILED","execution_user":"admin","execution_dateStartedUnixtime":1445866613841,"execution_description":"","execution_argstring":null,"execution_project":"hello-world","execution_loglevel":"INFO","execution_failedNodeListString":"localhost","execution_failedNodeList":["localhost"],"execution_succeededNodeListString":null,"execution_succeededNodeList":null,"execution_nodestatus":{"succeeded":0,"failed":1,"total":1},"execution_dateEndedUnixtime":1445866616505,"execution_abortedby":null,"job_id":"fdf1cca5-e729-491b-a1fa-3d8fef373d46","job_href":"http://xxx.xxx.xx.xx:4440/project/hello-world/job/show/fdf1cca5-e729-491b-a1fa-3d8fef373d46","job_name":"hello-world","job_group":"","job_project":"hello-world","job_description":"","job_averageDuration":758}
2015-10-26 22:43:51 +0900 rundeck.notification.hello-world: {"execution_id":2673,"execution_href":"http://xxx.xxx.xx.xx:4440/project/hello-world/execution/follow/2673","execution_status":"RUNNING","execution_user":"admin","execution_dateStartedUnixtime":1445867029642,"execution_description":"","execution_argstring":null,"execution_project":"hello-world","execution_loglevel":"INFO","execution_failedNodeListString":null,"execution_failedNodeList":null,"execution_succeededNodeListString":null,"execution_succeededNodeList":null,"execution_nodestatus":null,"execution_dateEndedUnixtime":null,"execution_abortedby":null,"job_id":"fdf1cca5-e729-491b-a1fa-3d8fef373d46","job_href":"http://xxx.xxx.xx.xx:4440/project/hello-world/job/show/fdf1cca5-e729-491b-a1fa-3d8fef373d46","job_name":"hello-world","job_group":"","job_project":"hello-world","job_description":"","job_averageDuration":758}
2015-10-26 22:43:53 +0900 rundeck.notification.hello-world: {"execution_id":2673,"execution_href":"http://xxx.xxx.xx.xx:4440/project/hello-world/execution/follow/2673","execution_status":"FAILED","execution_user":"admin","execution_dateStartedUnixtime":1445867029642,"execution_description":"","execution_argstring":null,"execution_project":"hello-world","execution_loglevel":"INFO","execution_failedNodeListString":"localhost","execution_failedNodeList":["localhost"],"execution_succeededNodeListString":null,"execution_succeededNodeList":null,"execution_nodestatus":{"succeeded":0,"failed":1,"total":1},"execution_dateEndedUnixtime":1445867032927,"execution_abortedby":null,"job_id":"fdf1cca5-e729-491b-a1fa-3d8fef373d46","job_href":"http://xxx.xxx.xx.xx:4440/project/hello-world/job/show/fdf1cca5-e729-491b-a1fa-3d8fef373d46","job_name":"hello-world","job_group":"","job_project":"hello-world","job_description":"","job_averageDuration":758}
```
