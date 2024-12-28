package com.segaExamples.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Swagger Link  :
 *                  <a href="http://localhost:8084/userService/swagger-ui/index.html">...</a>
 * This is the main class of Order service.
 */

@SpringBootApplication
@Slf4j
public class UserServiceStartUp {
    public static void main(String[] args) {
        log.info("Start the Users Service ----");
        SpringApplication.run(UserServiceStartUp.class);
        log.info("Started the Users Service-----");
    }
}