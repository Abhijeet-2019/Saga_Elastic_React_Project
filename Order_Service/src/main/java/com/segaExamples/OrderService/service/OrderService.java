package com.segaExamples.OrderService.service;

import com.segaExamples.OrderService.Repositories.OrderRepositories;
import com.segaExamples.OrderService.models.Items;
import com.segaExamples.OrderService.models.Orders;
import com.segaExamples.commonService.utils.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Autowired
    private OrderRepositories orderRepositories;

    /**
     * Persisting The Order and sending Order details for publishing
     *
     * @param paramOrders :
     * @return :
     *              Status of the Order.
     */
    @Transactional
    public String placeOrderForCustomer(Orders paramOrders) {
        Orders orders = new Orders();
        List<Items> itemsList = new ArrayList<>();
        try {
            orders.setOrderStatus(OrderStatus.ORDER_PlACED.toString());
            orders.setTransactionDate(paramOrders.getTransactionDate());
            orders.setCustomerId(paramOrders.getCustomerId());
            paramOrders.getItemsList().stream().forEach(items -> {
                Items items1 = new Items();
                items1.setItemCode(items.getItemCode());
                items1.setItemPrice(items.getItemPrice());
                items1.setItemQuantity(items.getItemQuantity());
                orders.addItemsInOrder(items);
            });

            log.info("The Order---->"+orders);
            orderRepositories.save(orders);
        }catch (Exception e){
            e.printStackTrace();
            return "There is an issue " +e.getMessage();
        }
        if(itemsList.isEmpty()) {
            orderEventPublisher.publishOrderEvent(orders.getCustomerId(), itemsList);
        }
        return  "Order placed successfully";
    }
}
