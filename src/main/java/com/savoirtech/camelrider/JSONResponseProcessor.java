package com.savoirtech.camelrider;

import com.savoirtech.camelrider.domain.OrderResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class JSONResponseProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {

        OrderResponse resp = new OrderResponse();
        resp.setStatus("Thank you for your order!");
        exchange.getOut().setBody(resp);

    }

}
