package com.apeng.smartlogisticsbackend.entity;


import com.apeng.smartlogisticsapp.service.dto.Warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shelve {


    private Long id;
    private int posX, posY;
    private int capacity;
    private int loadFactor;
    private Warehouse warehouse;

    // Constructor with Warehouse parameter
    public Shelve(int posX, int posY, Warehouse warehouse) {
        this.posX = posX;
        this.posY = posY;
        this.capacity = 4; // Fixed capacity for shelves
        this.warehouse = warehouse;
    }

}
