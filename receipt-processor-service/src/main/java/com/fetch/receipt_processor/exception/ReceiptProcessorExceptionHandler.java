package com.fetch.receipt_processor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReceiptProcessorExceptionHandler {

    @ExceptionHandler(value = {ReceiptNotFoundException.class})
    public ResponseEntity<String> handleMissingReceiptId(ReceiptNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleElse(Exception ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
