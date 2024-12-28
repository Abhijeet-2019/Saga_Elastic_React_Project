package com.segaExamples.InventoryService.configuration;


import com.hazelcast.config.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hazelcast.config.MaxSizePolicy;

/**
 * This is the main class for Hazel cast configuration..
 */
@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config hazelcastConfig() {
        Config config = new Config();

        // Configure a map
        MapConfig mapConfig = new MapConfig()
                .setName("elasticCacheMap")
                .setEvictionConfig(new EvictionConfig().setSize(200)
                        .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                        .setEvictionPolicy(EvictionPolicy.LRU))
                .setTimeToLiveSeconds(3600);
        config.addMapConfig(mapConfig);
        return config;
    }

}
