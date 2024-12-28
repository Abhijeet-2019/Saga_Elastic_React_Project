package com.segaExamples.OrderService.models;



import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import jakarta.persistence.* ;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "order_generator")
    @SequenceGenerator(name = "order_generator",
            sequenceName = "order_sequence",
            allocationSize = 1)
    @Column(name = "ORDER_ID")
    private int orderId;

    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @Column(name = "CUSTOMER_EMAIL")
    private String customerEmail;

    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @OneToMany(mappedBy = "orderDetails", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Items> itemsList;

    public void addItemsInOrder(Items item) {
        if(itemsList == null ){
            itemsList = new ArrayList<>();
        }
        this.itemsList.add(item);
        item.setOrderDetails(this);
    }
}
