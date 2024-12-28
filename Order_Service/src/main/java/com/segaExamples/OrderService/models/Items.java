package com.segaExamples.OrderService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import jakarta.persistence.* ;

@Getter
@Setter
@Entity
@Table(name = "ITEMS")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "item_generator")
    @SequenceGenerator(name = "item_generator",
            sequenceName = "item_sequence",
            allocationSize = 1)

    @Column(name = "ITEM_ID")
    private int itemId;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "ITEM_QUANTITY")
    private int itemQuantity;

    @Column(name = "ITEM_PRICE")
    private double itemPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    @Fetch(FetchMode.JOIN)
    private Orders orderDetails;

}
