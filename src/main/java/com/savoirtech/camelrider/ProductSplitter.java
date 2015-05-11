package com.savoirtech.camelrider;

import com.savoirtech.camelrider.domain.Item;
import com.savoirtech.camelrider.domain.Order;
import com.savoirtech.camelrider.domain.ProductOrder;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;

import java.util.ArrayList;
import java.util.List;

public class ProductSplitter {

    public List<Message> splitProducts(Order order){

        List<Message> messages = new ArrayList<Message>();

        for(Item item : order.getItems()){

            ProductOrder po = new ProductOrder();
            po.setCustomer(order.getCustomer());
            po.setProduct(item.getProduct());
            po.setQuanitity(item.getQuantity());

            DefaultMessage message = new DefaultMessage();
            message.setBody(po);

            //Set the header with the manufacturer
            if (item.getProduct().startsWith("abc")) {
                message.setHeader("manufacturer", "abc");
            } else if(item.getProduct().startsWith("xyz")) {
                message.setHeader("manufacturer", "xyz");
            }

            messages.add(message);

        }

        return messages;
    }

}
