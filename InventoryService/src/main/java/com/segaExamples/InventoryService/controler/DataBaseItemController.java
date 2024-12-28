package com.segaExamples.InventoryService.controler;

import com.segaExamples.InventoryService.model.InventoryDetailsTable;
import com.segaExamples.InventoryService.service.ProcessItemInventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@CrossOrigin
@RestController
@RequestMapping("/items")
@Slf4j
public class DataBaseItemController {

    // Uses a Database to persist the data .
    @Autowired
    ProcessItemInventory processItemInventory;

    @PostMapping("/addItems")
    public String addItemsToInventory(@RequestBody InventoryDetailsTable itemInventory) {
        log.info("Item received to be added {}", itemInventory);
        processItemInventory.addItemsInInventory(itemInventory);
        return "Items added";
    }

    @GetMapping("/fetchItems")
    public List<InventoryDetailsTable> fetchAllItems(){
        log.info("Fetching all details for items");
        return processItemInventory.fetchAllDetails();
    }

    /**
     *
     * @param itemCategory :
     * @return :
     *          The Item code and the price.
     */
    @GetMapping("/fetchItemsByCategory")
    public Map<String, String>
            fetchItemCodeByCategory(@RequestParam String itemCategory){
        log.info("fetching Item code by category {}, ", itemCategory);
        return processItemInventory.fetchItemCodeByCategory(itemCategory);
    }
}
