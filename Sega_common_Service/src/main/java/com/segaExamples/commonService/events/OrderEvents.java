package com.segaExamples.commonService.events;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;


@Getter
@Setter
public class OrderEvents {
    private String customerId;
    private List orderDetailsList;
    private String orderStatus;

}
