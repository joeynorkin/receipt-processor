package com.fetch.receipt_processor.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

public class ReceiptEntity {

    private String id;
    private String retailer;
    private LocalDateTime purchaseDateTime;

    private List<ItemEntity> items;
    private BigDecimal total;


    public ReceiptEntity() {

    }

    public ReceiptEntity(String retailer, LocalDateTime purchaseDateTime, List<ItemEntity> items, BigDecimal total) {
        this.retailer = retailer;
        this.purchaseDateTime = purchaseDateTime;
        this.items = items;
        this.total = total.setScale(2, RoundingMode.HALF_EVEN);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total.setScale(2, RoundingMode.HALF_EVEN);;
    }
}
