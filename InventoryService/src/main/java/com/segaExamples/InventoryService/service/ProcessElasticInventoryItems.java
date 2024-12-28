package com.segaExamples.InventoryService.service;


import com.segaExamples.InventoryService.controler.ElasticRestService;
import com.segaExamples.commonService.models.InventoryDetailsElastic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class uses  ElasticSearch  to persist all Inventory Details .
 */
@Service
@Slf4j
public class ProcessElasticInventoryItems {
    @Autowired
    ElasticRestService elasticRestService;

    @Autowired
    CacheService cacheService;


    public ResponseEntity<InventoryDetailsElastic> addItemsInElasticService(InventoryDetailsElastic itemInventory) {
//        InventoryDetailsElastic cacheUpdatedData = updateCache(itemInventory.getItemCategory(), itemInventory);
        cacheService.put(itemInventory.getItemCategory(),itemInventory);
        return elasticRestService.addItemsInElasticService(itemInventory);
    }


    @CachePut(value = "elasticCacheMap", key = "#key")// This is used when we add an Item code in the cache
    private InventoryDetailsElastic updateCache(String key, InventoryDetailsElastic inventoryDetailsElastic) {
        return inventoryDetailsElastic;
    }

    @Cacheable("elasticCacheMap")
    public List<InventoryDetailsElastic> fetchedSelectedItems(String nameSearch, String categorySearch, String sellerName) {
        log.info("Fetching data based on selected Items");
        List<InventoryDetailsElastic> fetchedItemList = new ArrayList<InventoryDetailsElastic>();
        ResponseEntity data = elasticRestService.fetchedSelectedItems(nameSearch, categorySearch, sellerName);
        log.info("Fetching data based on selected Items" + data.getBody());
        fetchedItemList.addAll((List) data.getBody());
        return fetchedItemList;
    }


    public ResponseEntity<List<InventoryDetailsElastic>> loadInitialElasticSearchItems(int fetchSize, int offset) {
        return elasticRestService.loadInitialElasticSearchItems(fetchSize, offset);
    }
}
