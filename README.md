# camel-rider
Example code for the "How to Ride with Apache Camel to Get Over Development Humps" presentation

Congratulations! You are likely reading this because you attended my session "How to Ride with Apache Camel to Get Over Development Humps".  The presentation does an overview of building code on a use case for utilizing Camel to process an order with an online restful servive API that takes an order with multiple line items.  The goal is to take this order and break down each line item to a specific manufacturer and send that order to each manufacturers processing locations.

The goal is to demonstrate building up ona use case that uses a restful service (JAXRS) that takes a JSON order, repson to the caller with an order accpeted, and sending to via persistenc messaging to EIPs using a splitter, messaging, and the file component.  The code demonstrates transofrmation from the original order into multiple individual order based on the manufacturer of the widget.

This project requires ActiveMQ 5.11.1 (or better) and a tool that allows submission of Restful "PUT" JSON payloads (IntelliJ, SOAPUI, Javascript).

To build this project, run the following at the root of the project:
 
<code>mvn clean install</code> 

To run it, first run ActiveMQ.  You can learn how to launch ActiveMQ [here](http://activemq.apache.org/getting-started.html#GettingStarted-StartingActiveMQ).

This project requires ActiveMQ 5.11.1 (or better) and it should be running when executing the project.  To see it work, run the application using the maven cmael plugin with the following command at the root of the camel-rider project:

<code>mvn camel:run<code>

When launching you should see something imilar to this:

<pre>
<code>
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building Savoir :: How To Ride Camel To Get OVer Development Humps 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
...
2015-05-10T21:34:41.605 INFO  [org.apache.cxf.endpoint.ServerImpl] - Setting the server's publish address to be http://localhost:9090/rest
2015-05-10T21:34:41.765 INFO  [org.eclipse.jetty.server.Server] - jetty-8.1.15.v20140411
2015-05-10T21:34:41.818 INFO  [org.eclipse.jetty.server.AbstractConnector] - Started SelectChannelConnector@localhost:9090
2015-05-10T21:34:41.837 INFO  [org.apache.camel.spring.SpringCamelContext] - Route: route1 started and consuming from: Endpoint[cxfrs://bean:orderService]
2015-05-10T21:34:42.024 INFO  [org.apache.camel.spring.SpringCamelContext] - Route: route2 started and consuming from: Endpoint[jmsConsumer://queue:orders?disableReplyTo=true]
2015-05-10T21:34:42.034 INFO  [org.apache.camel.spring.SpringCamelContext] - Route: route3 started and consuming from: Endpoint[jmsConsumer://queue:abc_company?disableReplyTo=true]
2015-05-10T21:34:42.044 INFO  [org.apache.camel.spring.SpringCamelContext] - Route: route4 started and consuming from: Endpoint[jmsConsumer://queue:xyz_company?disableReplyTo=true]
2015-05-10T21:34:42.045 INFO  [org.apache.camel.spring.SpringCamelContext] - Total 4 routes, of which 4 is started.
2015-05-10T21:34:42.046 INFO  [org.apache.camel.spring.SpringCamelContext] - Apache Camel 2.15.2 (CamelContext: camel) started in 1.111 seconds
</code>
</pre>

If you see these messages, then your ActiveMQ instance is not running or its not listening on port 61616 (or its not set up properly:

<pre><code>
2015-05-10T21:39:11.676 INFO  [org.apache.camel.component.jms.DefaultJmsMessageListenerContainer] - JMS message listener invoker needs to establish shared Connection
2015-05-10T21:39:11.676 INFO  [org.apache.camel.component.jms.DefaultJmsMessageListenerContainer] - JMS message listener invoker needs to establish shared Connection
2015-05-10T21:39:11.678 INFO  [org.apache.camel.component.jms.DefaultJmsMessageListenerContainer] - JMS message listener invoker needs to establish shared Connection
2015-05-10T21:39:11.681 ERROR [org.apache.camel.component.jms.DefaultJmsMessageListenerContainer] - Could not refresh JMS Connection for destination 'abc_company' - retrying using FixedBackOff{interval=5000, currentAttempts=0, maxAttempts=unlimited}. Cause: Could not connect to broker URL: tcp://localhost:61616. Reason: java.net.ConnectException: Connection refused
2015-05-10T21:39:11.681 ERROR [org.apache.camel.component.jms.DefaultJmsMessageListenerContainer] - Could not refresh JMS Connection for destination 'xyz_company' - retrying using FixedBackOff{interval=5000, currentAttempts=0, maxAttempts=unlimited}. Cause: Could not connect to broker URL: tcp://localhost:61616. Reason: java.net.ConnectException: Connection refused
2015-05-10T21:39:11.681 ERROR [org.apache.camel.component.jms.DefaultJmsMessageListenerContainer] - Could not refresh JMS Connection for destination 'orders' - retrying using FixedBackOff{interval=5000, currentAttempts=0, maxAttempts=unlimited}. Cause: Could not connect to broker URL: tcp://localhost:61616. Reason: java.net.ConnectException: Connection refused
</code></pre>

Fixing ActiveMQ is beyond the scope of this, so if you can't get it you should chat with the folks over at ActiveMQ or Camel dev lists.

To execute the call to teh restful service, besure that you set your tool/code/whatever to use the PUT verb and point it to:

http://localhost:9090/rest/order/add/

Your payload should look something like this:

<pre><code>
{
  "order": {
    "customer": {
      "lastName": "Hessla",
      "firstName": "Heaf",
      "address": "1234 Main St",
      "city": "Jackson Hole",
      "state": "WY",
      "zip": "83001"
    },
    "items": [
      {
        "product": "abc widget",
        "quantity": 2
      },
      {
        "product": "xyz widget",
        "quantity": 1
      }
    ]
  }
}
</code></pre>

You can copy and paste it fomr above or use the example.json in the root directory of this project.

To see the results, the target directoy should have a manufacturers subdirectory in it.  That directory should have 2 additional sub direcorties in it, abc and xyz.  In those directories you will have file names in the format "order-yyyyMMddHHmmss.json".  These files and directories will appear after you successfully submit a properly formed json payload like the one shown above.
