package com.fetch.receipt_processor.controller;

import com.fetch.receipt_processor.domain.Item;
import com.fetch.receipt_processor.domain.ProcessReceiptResponse;
import com.fetch.receipt_processor.domain.Receipt;
import com.fetch.receipt_processor.domain.ReceiptPointsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReceiptControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final List<Item> items1 = List.of(
            new Item("Mountain Dew 12PK", new BigDecimal("6.49")),
            new Item("Emils Cheese Pizza", new BigDecimal("12.25")),
            new Item("Knorr Creamy Chicken", new BigDecimal("1.26")),
            new Item("Doritos Nacho Cheese", new BigDecimal("3.35")),
            new Item("   Klarbrunn 12-PK 12 FL OZ  ", new BigDecimal("12.00"))
    );
    private final Receipt receipt1 = new Receipt(
            "Target",
            LocalDate.of(2022, 1, 1),
            LocalTime.of(13, 1),
            items1,
            new BigDecimal("35.35")
    );

    private final List<Item> items2 = List.of(
            new Item("Gatorade", new BigDecimal("2.25")),
            new Item("Gatorade", new BigDecimal("2.25")),
            new Item("Gatorade", new BigDecimal("2.25")),
            new Item("Gatorade", new BigDecimal("2.25"))
    );
    private final Receipt receipt2 = new Receipt(
            "M&M Corner Market",
            LocalDate.of(2022, 3, 20),
            LocalTime.of(14, 33),
            items2,
            new BigDecimal("9.00")
    );

    @Test
    public void processReceipt() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Receipt> requestEntity = new HttpEntity<>(receipt1, headers);
        ProcessReceiptResponse processResponse = restTemplate.postForObject("http://localhost:" + port + "/receipts/process", requestEntity, ProcessReceiptResponse.class);

        ReceiptPointsResponse receiptPointsResponse = restTemplate.getForObject("http://localhost:" + port + "/receipts/" + processResponse.getId() + "/points", ReceiptPointsResponse.class);
        assertThat(receiptPointsResponse.getPoints()).isEqualTo(28);
    }
}