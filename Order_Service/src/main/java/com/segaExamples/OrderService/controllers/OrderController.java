package com.segaExamples.OrderService.controllers;


import com.segaExamples.OrderService.models.Orders;
import com.segaExamples.OrderService.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * Order controllers
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/orderService")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * This method takes in all the details related to the Customer & Order
     * and places the order.
     *
     * @param orders :
     * @return
     */
    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody Orders orders) {
        log.info("Received the order for " +
                "the user {}-->", orders.getCustomerEmail());
        return orderService.placeOrderForCustomer(orders);
    }
}
