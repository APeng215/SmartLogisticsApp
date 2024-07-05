package com.apeng.smartlogisticsapp.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String mallName;
    private BigDecimal price;

    public Product(String name, String mallName, BigDecimal price) {
        this.name = name;
        this.mallName = mallName;
        this.price = price;
    }

}
