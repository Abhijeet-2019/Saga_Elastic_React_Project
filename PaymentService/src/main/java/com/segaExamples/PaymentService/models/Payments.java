package com.segaExamples.PaymentService.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Payments {

    private int paymentId;
    private int orderId;
    private String paymentStatus;
    private Timestamp paymentTime;
}
