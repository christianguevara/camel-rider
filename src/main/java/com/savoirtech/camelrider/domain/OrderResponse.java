package com.savoirtech.camelrider.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrderResponse")
public class OrderResponse {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
