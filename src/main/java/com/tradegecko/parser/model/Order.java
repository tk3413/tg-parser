package com.tradegecko.parser.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Order {

    private BigInteger objectId;

    private String customerName;

    private String customerAddress;

    private String status;

    private String shipDate;

    private String shippingProvider;

    private BigInteger timestamp;
}
