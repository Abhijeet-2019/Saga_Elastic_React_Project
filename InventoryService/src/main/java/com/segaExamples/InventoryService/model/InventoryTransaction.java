package com.segaExamples.InventoryService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "INVENTORY_TRANSACTION")
public class InventoryTransaction {

    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "transaction_generator")
    @SequenceGenerator(name = "transaction_generator",
            sequenceName = "transaction_sequence",
            allocationSize = 1)
    @Id
    @Column(name = "TRANSACTION_ID")
    private  int transactionId;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "CUSTOMER_EMAIL")
    private String customerEmail;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "TRANSACTION_QUANTITY")
    private int  transactionQuantity;

    @Column(name = "TRANSACTION_DATE")
    private Timestamp transactionDate;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private InventoryDetailsTable inventoryDetails;
}
