package com.assign.api.iot.data_ingestion_service.service;

import com.assign.api.iot.data_ingestion_service.exceptions.KafkaMessageConsumeException;
import com.assign.api.iot.data_ingestion_service.model.IoTData;
import com.assign.api.iot.data_ingestion_service.repository.IoTDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class IoTDataProcessorService {
    private static final Logger logger = LoggerFactory.getLogger(IoTDataProcessorService.class);
    @Autowired
    private IoTDataRepository ioTDataRepository;

    @KafkaListener(topics = "iot-data-topic",groupId = "iot-ingestion-group")
    public void processData(String message){
        IoTData data = null;
        try {
            data = new ObjectMapper().readValue(message, IoTData.class);
            IoTData entity = new IoTData(
                    null,
                    data.getDeviceId(),
                    data.getSensorDeviceType(),
                    data.getTimestamp(),
                    data.getValue());
            ioTDataRepository.save(entity);
            logger.debug("Entity saved in DB: {}", entity);
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse message in data-ingestion-service ",e);
            throw new KafkaMessageConsumeException("Deserialization failed for message: "+ e.getMessage());
        }catch (Exception e) {
            logger.error("Unexpected error occurred in data-ingestion-service: ", e);
            throw new KafkaMessageConsumeException("Kafka message parsing failed: "+ e.getMessage());
        }
    }
}


