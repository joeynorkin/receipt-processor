package com.fetch.receipt_processor.domain;

public class ProcessReceiptResponse {

    private String id;

    public ProcessReceiptResponse() {
    }

    public ProcessReceiptResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}