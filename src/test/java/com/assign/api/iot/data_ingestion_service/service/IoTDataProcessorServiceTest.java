package com.assign.api.iot.data_ingestion_service.service;

import com.assign.api.iot.data_ingestion_service.model.IoTData;
import com.assign.api.iot.data_ingestion_service.repository.IoTDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IoTDataProcessorServiceTest {
    @Mock
    private IoTDataRepository ioTDataRepository;

    @InjectMocks
    private IoTDataProcessorService ioTDataProcessorService;

    @Test
    void testProcessData_Success() throws JsonProcessingException {
        String message = new ObjectMapper().writeValueAsString(Map.of(
                "deviceId", "12345",
                "sensorDeviceType", "THERMOSTAT",
                "timestamp", System.currentTimeMillis(),
                "value", 45.67
        ));
        IoTData expectedEntity = new IoTData(null, "12345","THERMOSTAT",System.currentTimeMillis(),45.67);

        when(ioTDataRepository.save(any(IoTData.class))).thenReturn(expectedEntity);

        ioTDataProcessorService.processData(message);

        verify(ioTDataRepository, times(1)).save(any(IoTData.class));
    }

    @Test
    void testProcessData_Failure() {

        String invalidMessage = "{invalidJsonString}";
        ioTDataProcessorService.processData(invalidMessage);
        verify(ioTDataRepository, never()).save(any(IoTData.class));
    }

}
