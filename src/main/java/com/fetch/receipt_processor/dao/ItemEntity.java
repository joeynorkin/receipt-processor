package com.fetch.receipt_processor.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ItemEntity {

    private String shortDescription;
    private BigDecimal price;

    public ItemEntity(String shortDescription, BigDecimal price) {
        this.shortDescription = shortDescription;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);;
    }
}
