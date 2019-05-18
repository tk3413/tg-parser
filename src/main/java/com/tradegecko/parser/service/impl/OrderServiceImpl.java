package com.tradegecko.parser.service.impl;

import com.google.gson.Gson;
import com.tradegecko.parser.dao.impl.OrderDaoImpl;
import com.tradegecko.parser.model.Order;
import com.tradegecko.parser.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;

import static com.tradegecko.parser.utils.OrderConstants.*;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDaoImpl orderDao;

    @Override
    public Order produceOrderRequest(String[] line) {

        Order orderRequest = new Order();
            orderRequest.setObjectId(BigInteger.valueOf(Long.parseLong(line[0])));
            orderRequest.setTimestamp(BigInteger.valueOf(Long.parseLong(line[2])));

        Map changes = new Gson().fromJson(line[3], Map.class);
            if(changes.get(CUSTOMER_NAME) != null) {
                orderRequest.setCustomerName(changes.get(CUSTOMER_NAME).toString());
            }
            if(changes.get(CUSTOMER_ADDRESS) != null) {
                orderRequest.setCustomerAddress(changes.get(CUSTOMER_ADDRESS).toString());
            }
            if(changes.get(STATUS) != null) {
                orderRequest.setStatus(changes.get(STATUS).toString().toUpperCase());
            }
            if(changes.get(SHIP_DATE) != null) {
                orderRequest.setShipDate(changes.get(SHIP_DATE).toString());
            }
            if(changes.get(SHIPPING_PROVIDER) != null) {
                orderRequest.setShippingProvider(changes.get(SHIPPING_PROVIDER).toString());
            }

        System.out.println(orderRequest.toString());
        return orderRequest;
    }

    @Override
    public int post(Order order) {

        return orderDao.post(order);
    }
}
