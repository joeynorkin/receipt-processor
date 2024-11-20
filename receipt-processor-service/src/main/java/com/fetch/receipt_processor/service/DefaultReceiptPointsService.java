package com.fetch.receipt_processor.service;

import com.fetch.receipt_processor.dao.ItemEntity;
import com.fetch.receipt_processor.dao.ReceiptEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class DefaultReceiptPointsService implements ReceiptPointsService {
    @Override
    public int calculateRetailerPoints(ReceiptEntity receiptEntity) {
        return (int) receiptEntity.getRetailer().chars()
                .filter(Character::isLetterOrDigit)
                .count();
    }

    @Override
    public int calculateTotalPoints(ReceiptEntity receiptEntity) {
        BigDecimal total = receiptEntity.getTotal();
        int points = 0;
        if (total.stripTrailingZeros().scale() <= 0) {
            points += 50;
        }

        if (total.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
            points += 25;
        }

        return points;
    }

    @Override
    public int calculateItemsPoints(ReceiptEntity receiptEntity) {
        List<ItemEntity> items = receiptEntity.getItems();
        int points = 0;

        points += (items.size() / 2) * 5;

        points += items.stream()
                .filter(item -> item.getShortDescription().trim().length() % 3 == 0)
                .mapToInt(item -> item.getPrice().multiply(new BigDecimal("0.2")).setScale(0, RoundingMode.UP).intValue())
                .sum();

        return points;
    }

    @Override
    public int calculatePurchaseDatePoints(ReceiptEntity receiptEntity) {
        LocalDate localDate = receiptEntity.getPurchaseDateTime().toLocalDate();

        if (localDate.getDayOfMonth() % 2 != 0) {
            return 6;
        }

        return 0;
    }

    @Override
    public int calculatePurchaseTimePoints(ReceiptEntity receiptEntity) {
        LocalTime localTime = receiptEntity.getPurchaseDateTime().toLocalTime();
        LocalTime start = LocalTime.parse("14:00:00");
        LocalTime stop = LocalTime.parse("16:00:00");
        if (localTime.isAfter(start) && localTime.isBefore(stop)) {
            return 10;
        }

        return 0;
    }
}
