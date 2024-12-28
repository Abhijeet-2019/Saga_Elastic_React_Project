package com.segaExamples.InventoryService.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@Slf4j
public class OrderEventReceiverListener {

    @Autowired
    private ProcessOrders processOrders;

    /**
     * This method is used to receive the Order created event
     *
     * @param orderEvent : receives the Order event from the
     *
     */
    @KafkaListener(topics = "saga_order_topic", groupId = "orders-group")
    public void receiveOrderEvents(Object orderEvent , Acknowledgment ack) throws JsonMappingException, JsonProcessingException {
        log.info("Received the message from Order service for Order event");
        try{
            LinkedHashMap<String,Object> orderDetailsRecords = (LinkedHashMap<String, Object>) ((ConsumerRecord) orderEvent).value();
            log.info("The Order received--------------{}",orderDetailsRecords);
            processOrders.processOrders(orderDetailsRecords);
            ack.acknowledge();
        }catch (Exception exception){
            ack.acknowledge();
            log.error("Error occurred while processing Order Event");
        }
    }
}
