package com.fetch.receipt_processor.controller;

import com.fetch.receipt_processor.domain.ProcessReceiptResponse;
import com.fetch.receipt_processor.domain.Receipt;
import com.fetch.receipt_processor.domain.ReceiptPointsResponse;
import com.fetch.receipt_processor.service.ReceiptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/{id}/points")
    public ReceiptPointsResponse getPoints(@PathVariable String id) {
        return new ReceiptPointsResponse(receiptService.getPoints(id));
    }

    @PostMapping("/process")
    public ProcessReceiptResponse processReceipt(@RequestBody Receipt receipt) {
        return new ProcessReceiptResponse(receiptService.processReceipt(receipt));
    }

}
