package com.segaExamples.commonService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryTransaction {
     private  String customerEmail;
    private  String transactionType;
    private  String transactionQuantity ;
    private  String transactionDate ;

}
