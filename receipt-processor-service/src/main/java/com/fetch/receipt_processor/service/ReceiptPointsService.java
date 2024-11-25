package com.fetch.receipt_processor.service;

import com.fetch.receipt_processor.dao.ReceiptEntity;

public interface ReceiptPointsService {

    int calculateRetailerPoints(ReceiptEntity receiptEntity);

    int calculateTotalPoints(ReceiptEntity receiptEntity);

    int calculateItemsPoints(ReceiptEntity receiptEntity);

    int calculatePurchaseDatePoints(ReceiptEntity receiptEntity);

    int calculatePurchaseTimePoints(ReceiptEntity receiptEntity);

}
