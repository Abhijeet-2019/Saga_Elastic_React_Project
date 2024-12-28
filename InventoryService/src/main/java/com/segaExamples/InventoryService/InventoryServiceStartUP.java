package com.segaExamples.InventoryService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

/**
 * Swagger URL :
 *      <a href="http://localhost:8082/itemService/swagger-ui/index.html">...</a>
 *
 * The main start up class of Inventory application
 */
@SpringBootApplication
@Slf4j
@EnableFeignClients
@EnableCaching
@ImportAutoConfiguration({FeignAutoConfiguration.class})// Added this line because of addition of Feign client
public class InventoryServiceStartUP {
    public static void main(String[] args) {
        log.info("Starting the Inventory Micro Service ");
        SpringApplication.run(InventoryServiceStartUP.class);
        log.info("Started the Inventory Micro Service ");
    }
}