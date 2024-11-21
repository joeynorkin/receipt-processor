package com.fetch.receipt_processor.dao;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiptDao {

    private final IMap<String, ReceiptEntity> receipts;

    public ReceiptDao(HazelcastInstance hazelcastInstance) {
        this.receipts = hazelcastInstance.getMap("receipts");
    }

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
