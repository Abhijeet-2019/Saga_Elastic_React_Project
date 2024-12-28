package com.segaExamples.PaymentService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PaymentServiceAppStartUp {
    public static void main(String[] args) {
        log.info("------------Starting the Payment Service App----");
        SpringApplication.run(PaymentServiceAppStartUp.class);
        log.info("------------Started the Payment Service App----");
    }
}