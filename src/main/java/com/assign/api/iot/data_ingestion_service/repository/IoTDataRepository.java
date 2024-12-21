package com.assign.api.iot.data_ingestion_service.repository;

import com.assign.api.iot.data_ingestion_service.model.IoTData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IoTDataRepository extends JpaRepository<IoTData, Long> {
}
