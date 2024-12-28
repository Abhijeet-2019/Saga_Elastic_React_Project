package com.segaExamples.InventoryService.controler;


import com.segaExamples.InventoryService.model.InventoryDetailsTable;
import com.segaExamples.commonService.models.InventoryDetailsElastic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "elasticService", url = "http://localhost:8083/elasticService") // Replace with ElasticService URL
public interface ElasticRestService {

    @PostMapping("inventoryElastic/addItem")
    public ResponseEntity<InventoryDetailsElastic> addItemsInElasticService(@RequestBody InventoryDetailsElastic itemInventory);

    @GetMapping("inventoryElastic/fetchedSelectedItems")
    public ResponseEntity<List<InventoryDetailsElastic>> fetchedSelectedItems(@RequestParam(name = "nameSearch") String nameSearch,
                                                                          @RequestParam(name = "categorySearch") String categorySearch,
                                                                          @RequestParam(name = "sellerName") String sellerName);


    @PostMapping("inventoryElastic/bulkUpdateItems")
    public String addBulkItems(@RequestBody List<InventoryDetailsElastic> itemInventoryList);

    @GetMapping("inventoryElastic/loadAllItems")
    public ResponseEntity<List<InventoryDetailsElastic>> loadInitialElasticSearchItems(@RequestParam(name = "fetchSize") int fetchSize,
                                                                                          @RequestParam(name = "offset") int offset);
}
