package com.tradegecko.parser.service;

import com.tradegecko.parser.model.Order;

public interface OrderService {

    Order produceOrderRequest(String[] input);

    int post(Order order);
}
