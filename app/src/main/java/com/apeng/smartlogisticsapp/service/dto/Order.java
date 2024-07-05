package com.apeng.smartlogisticsapp.service.dto;

import com.apeng.smartlogisticsbackend.entity.Shelve;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private Date createTime;
    private Date updateTime;
    private String state; // “待入库” -> “已入库” -> “已出库” -> “已送达”
    private Warehouse targetWarehouse;
    private Car car;
    private Product product;
    private Shelve shelve;
    private int productNum = 1;

}

