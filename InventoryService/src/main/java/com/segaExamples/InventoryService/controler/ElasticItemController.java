package com.segaExamples.InventoryService.controler;


import com.segaExamples.InventoryService.service.ProcessElasticInventoryItems;
import com.segaExamples.commonService.models.InventoryDetailsElastic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/elasticItems")
@Slf4j
public class ElasticItemController {

    @Autowired
    ProcessElasticInventoryItems processElasticInventoryItems;


    @GetMapping("/initialLoadItems")
    public ResponseEntity<List<InventoryDetailsElastic>> initialLoadItems(@RequestParam(name = "fetchSize") int fetchSize, @RequestParam(name = "offset") int offset) {
        log.info("*******Called to fetch all Elastic Items*****");
        return processElasticInventoryItems.loadInitialElasticSearchItems(fetchSize, offset);

    }


    @PostMapping("/addItemInElastic")
    public ResponseEntity<InventoryDetailsElastic> addItemsToInventory(@RequestBody InventoryDetailsElastic itemInventory) {
        log.info("Item received to be added {}", itemInventory);
        return processElasticInventoryItems.addItemsInElasticService(itemInventory);
    }

    @GetMapping("/fetchedSelectedItems")
    public ResponseEntity<List<InventoryDetailsElastic>> fetchedSelectedItems(@RequestParam(name = "nameSearch") String nameSearch, @RequestParam(name = "categorySearch") String categorySearch, @RequestParam(name = "sellerName") String sellerName) {
        log.info("The Search Criteria is" +
                " Item Name -- {} &&" + "Item Category-- {} && Item Seller -- {} ", nameSearch, categorySearch, sellerName);
        List<InventoryDetailsElastic> fetchedItemSearched = processElasticInventoryItems.fetchedSelectedItems(nameSearch, categorySearch, sellerName);
        return ResponseEntity.status(HttpStatus.OK).body(fetchedItemSearched);
    }


    @PostMapping("/uploadBulkItems")
    public List<InventoryDetailsElastic> uploadBulkItemsInElastic(@RequestBody List<InventoryDetailsElastic> inventoryDetailsList) {
        List<InventoryDetailsElastic> uploadedList = new ArrayList<>();
        log.info("The data received from   UI----" + inventoryDetailsList);
        return uploadedList;
    }

    /**
     *
     * @param itemCode
     * @return
     */
    @GetMapping("/fetchItemCodeList")
    public ResponseEntity<Map<String, String>> fetchItemCodeList(@RequestParam(name = "itemCode") String itemCode) {
        log.info("The Selected Item" + itemCode);
        Map<String, String> itemDataMap = new HashMap<>();
        List<InventoryDetailsElastic> fetchedItemSearched = processElasticInventoryItems.fetchedSelectedItems("", itemCode, "");
        fetchedItemSearched.stream().forEach(item -> {
            itemDataMap.put(item.getItemCode(), String.valueOf(item.getItemPrice()));
        });
        return ResponseEntity.status(HttpStatus.OK).body(itemDataMap);
    }

}
