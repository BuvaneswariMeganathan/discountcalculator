package com.verramobility.discountcalculator.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Item {
    private int count;
    private String name;
    private double price;
    private boolean clearance;
}
