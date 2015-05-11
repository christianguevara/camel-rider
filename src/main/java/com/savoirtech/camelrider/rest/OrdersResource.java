package com.savoirtech.camelrider.rest;

import com.savoirtech.camelrider.domain.Order;
import com.savoirtech.camelrider.domain.OrderResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/order/")
public interface OrdersResource {

    @PUT
    @Path("/add/")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public OrderResponse addOrder(Order order);

}
