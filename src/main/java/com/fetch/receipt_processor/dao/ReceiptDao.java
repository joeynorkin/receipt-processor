package com.fetch.receipt_processor.dao;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptDao {

    //TODO: store in HZ map for multi node envs
    private final Map<String, ReceiptEntity> receipts = new ConcurrentHashMap<>();

    public ReceiptEntity create(ReceiptEntity entity) {
        String id = generateRandomId();
        entity.setId(id);
        receipts.put(id, entity);
        return receipts.get(id);
    }

    public ReceiptEntity get(String id) {
        return receipts.get(id);
    }


    private String generateRandomId() {
        return UUID.randomUUID().toString();
    }
}
