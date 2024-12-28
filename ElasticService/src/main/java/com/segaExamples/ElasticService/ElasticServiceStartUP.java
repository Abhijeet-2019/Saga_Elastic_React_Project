package com.segaExamples.ElasticService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@Slf4j
/**
 * Main Startup Class for ElasticSearch..
 */
public class ElasticServiceStartUP {
    public static void main(String[] args) {
      log.info("*********Starting the Elastic Service************");
        SpringApplication.run(ElasticServiceStartUP.class);
        log.info("***********Started  the Elastic Service*************");
    }
}