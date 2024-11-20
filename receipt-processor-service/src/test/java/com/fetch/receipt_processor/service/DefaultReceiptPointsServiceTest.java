package com.fetch.receipt_processor.service;

import com.fetch.receipt_processor.dao.ItemEntity;
import com.fetch.receipt_processor.dao.ReceiptEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultReceiptPointsServiceTest {

    private final DefaultReceiptPointsService service = new DefaultReceiptPointsService();

    private final List<ItemEntity> items1 = List.of(
            new ItemEntity("Mountain Dew 12PK", new BigDecimal("6.49")),
            new ItemEntity("Emils Cheese Pizza", new BigDecimal("12.25")),
            new ItemEntity("Knorr Creamy Chicken", new BigDecimal("1.26")),
            new ItemEntity("Doritos Nacho Cheese", new BigDecimal("3.35")),
            new ItemEntity("   Klarbrunn 12-PK 12 FL OZ  ", new BigDecimal("12.00"))
    );
    private final ReceiptEntity receiptEntity1 = new ReceiptEntity(
            "Target",
            LocalDateTime.of(2022, 1, 1, 13, 1),
            items1,
            new BigDecimal("35.35")
    );

    private final List<ItemEntity> items2 = List.of(
            new ItemEntity("Gatorade", new BigDecimal("2.25")),
            new ItemEntity("Gatorade", new BigDecimal("2.25")),
            new ItemEntity("Gatorade", new BigDecimal("2.25")),
            new ItemEntity("Gatorade", new BigDecimal("2.25"))
    );
    private final ReceiptEntity receiptEntity2 = new ReceiptEntity(
            "M&M Corner Market",
            LocalDateTime.of(2022, 3, 20, 14, 33),
            items2,
            new BigDecimal("9.00")
    );

    @Test
    public void calculateRetailerPoints() {
        assertThat(service.calculateRetailerPoints(receiptEntity1)).isEqualTo(6);
        assertThat(service.calculateRetailerPoints(receiptEntity2)).isEqualTo(14);
    }

    @Test
    public void calculateTotalPoints() {
        assertThat(service.calculateTotalPoints(receiptEntity1)).isEqualTo(0);
        assertThat(service.calculateTotalPoints(receiptEntity2)).isEqualTo(75);
    }

    @Test
    public void calculateItemsPoints() {
        assertThat(service.calculateItemsPoints(receiptEntity1)).isEqualTo(16);
        assertThat(service.calculateItemsPoints(receiptEntity2)).isEqualTo(10);
    }

    @Test
    public void calculatePurchaseDatePoints() {
        assertThat(service.calculatePurchaseDatePoints(receiptEntity1)).isEqualTo(6);
        assertThat(service.calculatePurchaseDatePoints(receiptEntity2)).isEqualTo(0);
    }

    @Test
    public void calculatePurchaseTimePoints() {
        assertThat(service.calculatePurchaseTimePoints(receiptEntity1)).isEqualTo(0);
        assertThat(service.calculatePurchaseTimePoints(receiptEntity2)).isEqualTo(10);
    }
}