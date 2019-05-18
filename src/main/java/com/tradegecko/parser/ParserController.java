package com.tradegecko.parser;

import com.opencsv.CSVReader;
import com.tradegecko.parser.model.Order;
import com.tradegecko.parser.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileReader;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ParserController {

    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping(path = "/parse", method = GET)
    public @ResponseBody void parse() {

        CSVReader reader;
        final Logger logger = LogManager.getLogger();
        String filePath = "src/main/resources/input.csv";

        try {

            reader = new CSVReader(new FileReader(filePath));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[1].equalsIgnoreCase("ORDER")) {
                    logger.info("Processing order request for object_id = " + line[0]);
                    Order orderRequest = orderService.produceOrderRequest(line);

                    logger.info("Order request is: " + orderRequest.toString());
                    orderService.post(orderRequest);
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
