package com.segaExamples.OrderService.service;



import com.segaExamples.OrderService.config.KafkaConsumerConfig;
import com.segaExamples.OrderService.models.Items;

import com.segaExamples.commonService.events.OrderEvents;
import com.segaExamples.commonService.utils.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderEventPublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    public void publishOrderEvent(int customerId, List<Items> customerItemList)  {
        OrderEvents orderEvents = new OrderEvents();
        orderEvents.setCustomerId(String.valueOf(customerId));
        orderEvents.setOrderDetailsList(customerItemList);
        orderEvents.setOrderStatus(OrderStatus.ORDER_PlACED.toString());
        try {
            kafkaTemplate.send("saga_order_topic", customerId + "", orderEvents);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    private void handleServiceDown() {
        System.out.println(" Unable to send message to the  consumer Hence cancel the order");
    }
}
