package com.fetch.receipt_processor.exception;

public class ReceiptNotFoundException extends RuntimeException {
    public ReceiptNotFoundException(String id) {
        super("Receipt does not exist for ID=" + id);
    }
}
