package com.segaExamples.InventoryService.service;


import com.segaExamples.InventoryService.Repositories.InventoryRepository;
import com.segaExamples.InventoryService.Repositories.ItemTransactionRepository;
import com.segaExamples.InventoryService.model.InventoryDetailsTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@Slf4j
public class ProcessOrders {

    @Autowired
    private InventoryRepository itemRepository;

    @Autowired
    private ItemTransactionRepository itemTransactionRepository;

    /**
     * This method is used to validate in case the inventory has items to serve the order.
     *
     * @param orderDetailsRecords :
     *                            Details of Order records.
     */
    public void processOrders(LinkedHashMap<String, Object> orderDetailsRecords) {
        String customerId = (String) orderDetailsRecords.get("customerId");
        List orderDetails = (List) orderDetailsRecords.get("orderDetailsList");
        Map <String, Boolean > itemAvailabilityMap = new HashMap<>();
        orderDetails.stream().forEach(order -> {
            String itemCode = (String) ((LinkedHashMap) order).get("itemCode");
            int quantity = (int) ((LinkedHashMap) order).get("itemQuantity");
            boolean checkItemAvailability = validateItemAvailability(itemCode, quantity);
            itemAvailabilityMap.put(itemCode, checkItemAvailability);
        });
          if(itemAvailabilityMap.containsKey(Boolean.FALSE)){
              // send a Rollback Event call to Order Service and return;
              return;
              }

    }



    /**
     * @param itemCode :
     *                 The Item code for which we need to fis
     * @param quantity :
     * @return :
     */
    private boolean validateItemAvailability(String itemCode, int quantity) {
        boolean itemAvailable = false;
        List<InventoryDetailsTable> itemInventoryDetails = itemRepository.findByItemCode(itemCode);
        if (!itemInventoryDetails.isEmpty()) {
            InventoryDetailsTable itemInventory = itemInventoryDetails.get(0);
            if (quantity >= itemInventory.getItemQuantity()) {
                itemAvailable = true;
            }
        }
        return itemAvailable;
    }
}
