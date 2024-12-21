package com.assign.api.iot.data_ingestion_service.exceptions;

public class KafkaMessageConsumeException extends RuntimeException {
    public KafkaMessageConsumeException(String message) {
        super(message);
    }
}