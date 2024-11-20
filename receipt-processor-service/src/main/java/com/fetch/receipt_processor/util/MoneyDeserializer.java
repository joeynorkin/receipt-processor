package com.fetch.receipt_processor.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyDeserializer extends JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String valueAsString = jsonParser.getValueAsString();
        if (valueAsString == null) {
            return null;
        }

        try {
            BigDecimal decimal = new BigDecimal(valueAsString);
            return decimal.setScale(2, RoundingMode.HALF_EVEN); // Set the desired scale here
        } catch (NumberFormatException e) {
            throw new JsonParseException(jsonParser, "Invalid decimal format", e);
        }
    }

}
