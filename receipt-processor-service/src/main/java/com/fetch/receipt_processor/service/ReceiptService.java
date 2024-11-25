package com.fetch.receipt_processor.service;

import com.fetch.receipt_processor.dao.ItemEntity;
import com.fetch.receipt_processor.dao.ReceiptDao;
import com.fetch.receipt_processor.dao.ReceiptEntity;
import com.fetch.receipt_processor.domain.Receipt;
import com.fetch.receipt_processor.exception.ReceiptNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {

    private final ReceiptDao receiptDao;
    private final ReceiptPointsService receiptPointsService;

    public ReceiptService(ReceiptDao receiptDao, ReceiptPointsService receiptPointsService) {
        this.receiptDao = receiptDao;
        this.receiptPointsService = receiptPointsService;
    }

    public String processReceipt(Receipt receipt) {
        return receiptDao.create(toEntity(receipt)).getId();
    }

    public int getPoints(String id) {
        Optional<ReceiptEntity> receiptEntity = receiptDao.get(id);
        if (receiptEntity.isEmpty()) {
            throw new ReceiptNotFoundException(id);
        }
        return calculatePoints(receiptEntity.get());
    }

    private int calculatePoints(ReceiptEntity receiptEntity) {
        int points = receiptPointsService.calculateRetailerPoints(receiptEntity);
        points += receiptPointsService.calculateTotalPoints(receiptEntity);
        points += receiptPointsService.calculateItemsPoints(receiptEntity);
        points += receiptPointsService.calculatePurchaseDatePoints(receiptEntity);
        points += receiptPointsService.calculatePurchaseTimePoints(receiptEntity);
        return points;
    }

    private ReceiptEntity toEntity(Receipt receipt) {
        LocalDate purchaseDate = receipt.getPurchaseDate();
        LocalTime purchaseTime = receipt.getPurchaseTime();

        LocalDateTime purchaseDateTime = LocalDateTime.of(
                purchaseDate.getYear(),
                purchaseDate.getMonth(),
                purchaseDate.getDayOfMonth(),
                purchaseTime.getHour(),
                purchaseTime.getMinute());

        List<ItemEntity> items = receipt.getItems().stream()
                .map(item -> new ItemEntity(item.shortDescription(), item.price()))
                .toList();

        return new ReceiptEntity(receipt.getRetailer(), purchaseDateTime, items, receipt.getTotal());
    }

}
