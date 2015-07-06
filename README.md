#Introduction
This project allows you to push [Sensu](https://sensuapp.org/) checks from a Spring application. This particularly 
useful when you want to send a status critical to Sensu on a runtime exception.

#How does it work
A critical status of your application is an exceptional state, so you annotate the suspicious method in your Spring 
application with `@NotifySensu`. Done.

De sensu-logger jar contains a AOP pointcut on any method that is annotated with `@NotifySensu`. The aspect is 
triggered after an exception is thrown.
 
The aspect reads the exception message and opens a _socket_ to Sensu. The default socket address is 
`localhost:3030`. You need to configure the port with the `sensu.client.port` property.

The JSON message has the following format:
```JSON
{
    "name" : ${sensu.app.name}
    "output" : your_exception_message,
    "status" : 2,
    "handler" : "flapjack",
    "type": "metrics"
}
```
The name and output are configurable.