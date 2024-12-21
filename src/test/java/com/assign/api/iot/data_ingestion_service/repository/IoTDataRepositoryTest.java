package com.assign.api.iot.data_ingestion_service.repository;

import com.assign.api.iot.data_ingestion_service.model.IoTData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IoTDataRepositoryTest {
    @Mock
    private IoTDataRepository ioTDataRepository;

    @Test
    void testSave_Success() {
        IoTData mockData = new IoTData(1L,"id-01","THERMOSTAT",1734422578553L,45.67);

        when(ioTDataRepository.save(mockData)).thenReturn(mockData);

        IoTData result = ioTDataRepository.save(mockData);

        assertNotNull(result);
        assertEquals("THERMOSTAT", result.getSensorDeviceType());
        verify(ioTDataRepository, times(1)).save(mockData);
    }

    @Test
    void testSave_Failure() {
        IoTData mockData = new IoTData(1L,"id-01","THERMOSTAT",1734422578553L,45.67);

        when(ioTDataRepository.save(mockData)).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ioTDataRepository.save(mockData));
        assertEquals("Database error", exception.getMessage());
        verify(ioTDataRepository, times(1)).save(mockData);
    }
}
