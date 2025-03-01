package com.segaExamples;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Starting the admin Server--");
        SpringApplication.run(Main.class);
        log.info("Starting the admin Started--");
    }
}