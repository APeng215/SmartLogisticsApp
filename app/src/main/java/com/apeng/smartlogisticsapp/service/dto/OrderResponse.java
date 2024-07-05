package com.apeng.smartlogisticsapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Order order;
    private int orderPrice;

    public OrderResponse(Order order) {
        this.order = order;
        this.orderPrice = order.getProductNum() * order.getProduct().getPrice().intValue();
    }

}
