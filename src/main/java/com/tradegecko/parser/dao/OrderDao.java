package com.tradegecko.parser.dao;

import com.tradegecko.parser.model.Order;

public interface OrderDao {
    public int post(Order order);
}
