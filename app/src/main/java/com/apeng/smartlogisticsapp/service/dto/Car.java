package com.apeng.smartlogisticsapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private Long id;
    private String transporter;
    private String state; // “停靠中” <---> “运输中”
    private Warehouse warehouse;
    private Warehouse targetWarehouse;

    /**
     * 创建一个默认为"停靠中"状态的车辆
     * @param transporter 驾驶员名称
     * @param warehouse 车所停靠的仓库
     */
    public Car(String transporter, Warehouse warehouse) {
        this.transporter = transporter;
        this.warehouse = warehouse;
        this.state = "停靠中";
    }

}
