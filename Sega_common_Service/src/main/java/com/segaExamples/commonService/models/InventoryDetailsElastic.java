package com.segaExamples.commonService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDetailsElastic {
    private String itemId;
    private String itemCode;
    private String itemCategory;
    private int itemQuantity;
    private int itemPrice;
    private String itemSoldBy;
    private String itemAddedDate;
    private InventoryTransaction itemTransaction;
}
