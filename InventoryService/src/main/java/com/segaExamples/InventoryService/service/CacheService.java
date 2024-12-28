package com.segaExamples.InventoryService.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CacheService {

    private final IMap<String, Object> cacheMap;

    @Autowired
    public CacheService(HazelcastInstance hazelcastInstance) {
        this.cacheMap = hazelcastInstance.getMap("elasticCacheMap");
    }

    // Add data to the cache
    public void put(String key, Object value) {
        log.info("adding key to cache {}---  for value-- ",key, value);
        cacheMap.put(key, value, 1, TimeUnit.HOURS); // TTL of 1 hour
    }

    // Retrieve data from the cache
    public Object get(String key) {
        log.info("fetching value for key{}",key);
        return cacheMap.get(key);
    }

    // Update the cache
    public void update(String key, Object value) {
        if (cacheMap.containsKey(key)) {
            cacheMap.put(key, value);
        }
    }

    // Remove an entry from the cache
    public void remove(String key) {
        cacheMap.delete(key);
    }
}
