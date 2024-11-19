package com.fetch.receipt_processor.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fetch.receipt_processor.util.MoneyDeserializer;

import java.math.BigDecimal;

public record Item(String shortDescription,
                   @JsonDeserialize(using = MoneyDeserializer.class) BigDecimal price) {}
