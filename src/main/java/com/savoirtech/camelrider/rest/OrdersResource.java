package com.savoirtech.camelrider.rest;

import com.savoirtech.camelrider.domain.Order;
import com.savoirtech.camelrider.domain.OrderResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/order/")
public interface OrdersResource {

    @POST
    @Path("/add/")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public OrderResponse addOrder(Order order);

}
