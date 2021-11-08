package com.carservice.carservice.entity;

import lombok.Getter;

@Getter
public enum CarType {
    BMW1("BMW", "Series 1"),
    BMW2("BMW", "Series 1 Coupe"),
    BMW3("BMW", "Series 2"),
    BMW4("BMW", "Series 3"),
    BMW5("BMW", "Series 3 Touring"),
    BMW6("BMW", "Series 4"),
    BMW7("BMW", "Series 4 Coupe"),
    BMW8("BMW", "Series 4 GrandCoupe"),
    BMW9("BMW", "Series 5"),
    BMW10("BMW", "Series 5 Touring"),
    BMW11("BMW", "Series 6"),
    BMW12("BMW", "Series 6 Coupe"),
    BMW13("BMW", "Series 6 GrandCoupe"),
    BMW14("BMW", "Series 7"),
    BMW15("BMW", "Series 8"),

    BMW16("BMW", "Series X1"),
    BMW17("BMW", "Series X2"),
    BMW18("BMW", "Series X3"),
    BMW19("BMW", "Series X4"),
    BMW20("BMW", "Series X5"),
    BMW21("BMW", "Series X6"),
    BMW22("BMW", "Series X7"),
    BMW23("BMW", "Series X8");

    private final String manufacturer;
    private final String model;

    CarType(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }
}
