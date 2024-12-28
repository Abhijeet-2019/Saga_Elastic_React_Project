package com.segaExamples.ElasticService.services;

import com.google.gson.Gson;
import com.segaExamples.ElasticService.Dao.ElasticDao;
import com.segaExamples.commonService.models.InventoryDetailsElastic;
import com.segaExamples.commonService.utils.ElasticException;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class ElasticInventoryService {


    @Autowired
    ElasticDao elasticDao;

    private static final  String INDEX_NAME = "inventorydetails_index";

    /**
     * Used to persist a Single record in ElasticSearch...
     *
     * @param itemInventory
     * @return
     * @throws ElasticException
     */
    public InventoryDetailsElastic persistData(final InventoryDetailsElastic itemInventory) throws ElasticException {
        String id = "";
        try {
            final XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject();
            xContentBuilder.field("itemCode", itemInventory.getItemCode());
            xContentBuilder.field("itemCategory", itemInventory.getItemCategory());
            xContentBuilder.field("itemQuantity", itemInventory.getItemQuantity());
            xContentBuilder.field("itemPrice", itemInventory.getItemPrice());
            xContentBuilder.field("itemSoldBy", itemInventory.getItemSoldBy());
            xContentBuilder.field("itemAddedDate", itemInventory.getItemAddedDate());
            xContentBuilder.startObject("itemTransaction");
            xContentBuilder.field("customerEmail", itemInventory.getItemTransaction().getCustomerEmail());
            xContentBuilder.field("transactionType", itemInventory.getItemTransaction().getTransactionType());
            xContentBuilder.field("transactionQuantity", itemInventory.getItemTransaction().getTransactionQuantity());
            xContentBuilder.field("transactionDate", itemInventory.getItemTransaction().getTransactionDate());
            xContentBuilder.endObject();
            xContentBuilder.endObject();
            id = elasticDao.saveDataInElasticIndex(INDEX_NAME, xContentBuilder);
        } catch (IOException exception) {
            log.error("Unable to Parse the data provided ");
        } catch (ElasticException e) {
            log.error("Unable to persist the data");
        }
        log.info(" added an element into Elastic Index with Id {}", id);
        if (!StringUtils.isBlank(id)) {
            itemInventory.setItemId(id);
        } else {
            throw new ElasticException("Unable to Insert Record with record name {}" + itemInventory.getItemCode());
        }
        return itemInventory;
    }

    /**
     * Used to fetch Details from Elastic based on certain criteria..
     *
     * @param nameSearch     : Name of Search Item
     * @param categorySearch : Category Name of Search Item
     * @param sellerName     : Seller name of Search Item
     * @return Return Type is a List of Items
     */
    public List<InventoryDetailsElastic> fetchedSelectedItems(String nameSearch, String categorySearch, String sellerName) throws ElasticException {
        final List<InventoryDetailsElastic> itemList = new ArrayList<InventoryDetailsElastic>();
        try {
            Map<String, String> criteriaMap = new HashMap<>();
            if (!nameSearch.equals("")) {
                criteriaMap.put("itemCode", nameSearch);
            }
            if (!categorySearch.equals("")) {
                criteriaMap.put("itemCategory", categorySearch);
            }
            if (!sellerName.equals("")) {
                criteriaMap.put("itemSoldBy", sellerName);
            }
            List<String> searchString =    elasticDao.searchItemBasedOnCriteria(INDEX_NAME, criteriaMap);
            if(searchString.isEmpty()){
                log.error("Unable to find any data related to search Criteria");
                throw new ElasticException("Unable to fetch Data for the search Criteria");
            }else {
                Gson gson = new Gson();

                searchString.stream().forEach(item -> {
                    InventoryDetailsElastic inventoryDetailsElastic
                            = gson.fromJson(item,
                            InventoryDetailsElastic.class);
                    log.info(" The fetched Object" + inventoryDetailsElastic);
                    itemList.add(inventoryDetailsElastic);
                });
            }
        }catch (Exception exception){
            throw new ElasticException("Unable to fetch Data for the search Criteria");
        }

        return itemList;
    }

    public void bulkUpdateItem(List<InventoryDetailsElastic> itemInventoryList) {
    }

    /**
     * This method is used to fetch the initial Items in ElasticSearch..
     * @param fetchSize
     * @param offset
     */
    public final List<InventoryDetailsElastic>  fetchInitialElasticItems(int fetchSize, int offset) {
        log.info("Called to load the initial Items from Elastic *****");
        final List<InventoryDetailsElastic> itemList = new ArrayList<InventoryDetailsElastic>();
        try {
            List<String> fetchAllItems = elasticDao.fetchAllItems(INDEX_NAME ,fetchSize, offset);
            Gson gson = new Gson();
            if(!fetchAllItems.isEmpty()){
                fetchAllItems.stream().forEach( item ->{
                    InventoryDetailsElastic inventoryDetailsElastic
                            = gson.fromJson(item,
                            InventoryDetailsElastic.class);
                    log.info(" The fetched Object"+inventoryDetailsElastic);
                    itemList.add(inventoryDetailsElastic);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return itemList;
    }
}
