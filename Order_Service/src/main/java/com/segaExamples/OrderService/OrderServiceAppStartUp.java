package com.segaExamples.OrderService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Swagger Link  :
 *        <a href="http://localhost:8080/orderService/swagger-ui/index.html">...</a>
 * This is the main class of Order service.
 */
@SpringBootApplication
@Slf4j
public class OrderServiceAppStartUp {

    public static void main(String[] args) {
        log.info("----Starting the Order Service App---");
        SpringApplication.run(OrderServiceAppStartUp.class);
        log.info("----Started the Order Service App---");
    }
}