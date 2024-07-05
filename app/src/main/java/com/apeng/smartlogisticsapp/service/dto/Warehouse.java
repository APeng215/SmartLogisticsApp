package com.apeng.smartlogisticsapp.service.dto;

import com.apeng.smartlogisticsapp.service.dto.embeddable.LngLat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {


    private Long id;
    private String name;
    private String position;
    private int capacity;
    private LngLat lngLat;

    public Warehouse(String name, String position, int capacity) {
        this.name = name;
        this.position = position;
        this.capacity = capacity;
    }

    public Warehouse(String name, String position, int capacity, LngLat lngLat) {
        this.name = name;
        this.position = position;
        this.capacity = capacity;
        this.lngLat = lngLat;
    }


}
