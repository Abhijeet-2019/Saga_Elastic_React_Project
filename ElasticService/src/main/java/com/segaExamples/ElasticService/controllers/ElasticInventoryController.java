package com.segaExamples.ElasticService.controllers;


import com.segaExamples.ElasticService.services.ElasticInventoryService;
import com.segaExamples.commonService.models.InventoryDetailsElastic;
import com.segaExamples.commonService.utils.ElasticException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/inventoryElastic")
@Slf4j
public class ElasticInventoryController {

    @Autowired
    ElasticInventoryService inventoryService;

    /**
     * This method is used to load  Items from the Elastic Index based on Offset and size.
     *
     * @param fetchSize
     * @param offset
     * @return
     */
    @GetMapping("loadAllItems")
    ResponseEntity<List<InventoryDetailsElastic>> loadAllItems(@RequestParam(name = "fetchSize") int fetchSize,
                                                               @RequestParam(name = "offset") int offset) {
        log.info("Initial Method to fetch the Initial Data from Elastic Search with fetch Size {} && offset --{}", fetchSize, offset);
        List<InventoryDetailsElastic> initialFetchData = new ArrayList<>();
        try {
            initialFetchData = inventoryService.fetchInitialElasticItems(fetchSize, offset);
            log.info("The Total Number of Items returned after Initial Search --->"+initialFetchData.size());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(initialFetchData);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(initialFetchData);
    }

    /**
     * @param itemInventory
     * @return
     */
    @PostMapping("/addItem")
    public ResponseEntity<InventoryDetailsElastic> addItemsToInventory(@RequestBody InventoryDetailsElastic itemInventory) {
        log.info("The Item that we need to add  {}", itemInventory);
        InventoryDetailsElastic inventoryDetailsObj = null;
        try {
            inventoryDetailsObj = inventoryService.persistData(itemInventory);
        } catch (ElasticException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(inventoryDetailsObj);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryDetailsObj);
    }


    /**
     * This method is used when we need to searh or fetch Items based on a criteria
     *
     * @param nameSearch
     * @param categorySearch
     * @param sellerName
     * @return
     */
    @GetMapping("/fetchedSelectedItems")
    public ResponseEntity<List<InventoryDetailsElastic>> fetchedSelectedItems(@RequestParam(name = "nameSearch") String nameSearch,
                                                                              @RequestParam(name = "categorySearch") String categorySearch,
                                                                              @RequestParam(name = "sellerName") String sellerName) {
        log.info("The Search Criteria is   Item Name --{}  &&  " +
                "Item Category-- {} && Item Seller -- {} ", nameSearch, categorySearch, sellerName);
        final List<InventoryDetailsElastic> inventoryDetailsObj = new ArrayList<>();
        try {
            inventoryDetailsObj.addAll(inventoryService.fetchedSelectedItems(nameSearch, categorySearch, sellerName));
        } catch (ElasticException exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryDetailsObj);
    }


    @PostMapping("inventoryElastic/bulkUpdateItems")
    public List<InventoryDetailsElastic> addBulkItems(@RequestBody List<InventoryDetailsElastic> itemInventoryList) {
        inventoryService.bulkUpdateItem(itemInventoryList);
        return new ArrayList<>();
    }
}
