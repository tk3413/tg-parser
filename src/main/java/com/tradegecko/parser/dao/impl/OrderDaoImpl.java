package com.tradegecko.parser.dao.impl;

import com.google.gson.Gson;
import com.tradegecko.parser.dao.OrderDao;
import com.tradegecko.parser.model.Order;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class OrderDaoImpl implements OrderDao {

    private final Logger log = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public int post(Order order) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/orderInfo");

        Gson gson = new Gson();
        String json = gson.toJson(order);
        StringEntity entity = null;

        try {

            entity = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        log.info("Entity: " + entity);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
        log.info("http post is:" + httpPost);

        try {

            CloseableHttpResponse response = client.execute(httpPost);
            client.close();
            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return 0;
    }
}
