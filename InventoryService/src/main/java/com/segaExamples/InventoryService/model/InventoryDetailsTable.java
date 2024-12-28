package com.segaExamples.InventoryService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Getter
@Setter
@Entity
@Table(name = "INVENTORY_DETAILS")
public class InventoryDetailsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "item_generator")
    @SequenceGenerator(name = "item_generator",
            sequenceName = "item_sequence",
            allocationSize = 1)
    @Column(name = "ITEM_ID")
    private int  itemId;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "ITEM_CATEGORY")
    private String itemCategory;

    @Column(name = "ITEM_QUANTITY")
    private int  itemQuantity;

    @Column(name = " ITEM_PRICE")
    private int  itemPrice;


    @OneToOne(mappedBy = "inventoryDetails", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private InventoryTransaction itemTransaction;

    /**
     * This is used to set the mapping of One to One
     * @param itemTransaction :
     *                                          Set the Transaction details for an item.
     */
    public void  addItemTransaction(InventoryTransaction itemTransaction){
        itemTransaction.setInventoryDetails(this);
        this.setItemTransaction(itemTransaction);
    }
}


