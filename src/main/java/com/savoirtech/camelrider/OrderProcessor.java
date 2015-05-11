package com.savoirtech.camelrider;

import com.savoirtech.camelrider.domain.OrderResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class OrderProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {

        Message msg = exchange.getIn();

        //Accept the order since by the time we got here, the JAXB stuff passed
        //You could continue doing data validation here is you wanted with similar code to this
        //   String operationName = msg.getHeader(CxfConstants.OPERATION_NAME, String.class);
        //
        //   if ("addOrder".equals(operationName)) {
        //       //Check that we have all of our data needs.
        //
        //
        //   } else {
        //       webFault(Response.Status.NOT_IMPLEMENTED, "Invalid operation", msg);
        //   }

        OrderResponse resp = new OrderResponse();
        resp.setStatus("Thank you for your order!");
        exchange.getOut().setBody(resp);
    }

    private void webFault(Response.Status code, String reason, Message msg) {
        Response r = Response.status(code).entity(reason).build();
        msg.getExchange().setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
        throw new WebApplicationException(r);
    }
}
