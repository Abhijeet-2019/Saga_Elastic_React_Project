package com.segaExamples.InventoryService.service;

import com.segaExamples.InventoryService.Repositories.InventoryRepository;
import com.segaExamples.InventoryService.model.InventoryDetailsTable;
import com.segaExamples.InventoryService.model.InventoryTransaction;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * THis class uses a database to persist .
 */
@Service
@Slf4j
public class ProcessItemInventory {

    @Autowired
    InventoryRepository itemRepository;

    /**
     * Persist all Item details in DB.
     *
     * @param itemInventory :
     */
    @Transactional
    public void addItemsInInventory(InventoryDetailsTable itemInventory) {
        log.info("Persist the item added  by {}", itemInventory.getItemTransaction().getCustomerEmail());
        try {
            InventoryDetailsTable inventoryDetails = new InventoryDetailsTable();
            inventoryDetails.setItemCategory(itemInventory.getItemCategory());
            inventoryDetails.setItemCode(itemInventory.getItemCode());
            inventoryDetails.setItemQuantity(itemInventory.getItemQuantity());
            inventoryDetails.setItemPrice(itemInventory.getItemPrice());

            InventoryTransaction inventoryTransaction = new InventoryTransaction();
            inventoryTransaction = itemInventory.getItemTransaction();
            inventoryTransaction.setCustomerId(String.valueOf(itemInventory.getItemTransaction().getTransactionId()));
            inventoryTransaction.setCustomerEmail(itemInventory.getItemTransaction().getCustomerEmail());
            inventoryTransaction.setTransactionQuantity(itemInventory.getItemTransaction().getTransactionQuantity());
            inventoryTransaction.setTransactionType(itemInventory.getItemTransaction().getTransactionType());
            inventoryTransaction.setTransactionDate(itemInventory.getItemTransaction().getTransactionDate());
            inventoryDetails.addItemTransaction(inventoryTransaction);

            itemRepository.save(inventoryDetails);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to persist the Items added {}", e.getMessage());
        }
    }

    public List<InventoryDetailsTable> fetchAllDetails() {
        return itemRepository.findAll();
    }

    public Map<String, String> fetchItemCodeByCategory(String itemCategory) {

        Map<String, String> itemMap = new HashMap<String, String>();
        try {
            List<InventoryDetailsTable> itemDetailList = itemRepository.findByItemCategory(itemCategory);
            log.info("the Item Details List ---" + itemDetailList);
            itemDetailList.forEach(item ->{
                itemMap.put(item.getItemCode(),String.valueOf(item.getItemPrice()));
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return itemMap;
    }
}
