package com.assign.api.iot.data_ingestion_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "iot_data")
public class IoTData {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "device_id", nullable = false)
        private String deviceId;
        @Column(name = "sensor_device_type", nullable = false)
        private String sensorDeviceType;
        @Column(name = "timestamp", nullable = false)
        private Long timestamp;
        @Column(name = "value", nullable = false)
        private Double value;

        public IoTData() {
        }

        public IoTData(Long id, String deviceId, String sensorDeviceType, Long timestamp, Double value) {
                this.id = id;
                this.deviceId = deviceId;
                this.sensorDeviceType = sensorDeviceType;
                this.timestamp = timestamp;
                this.value = value;
        }

        public Long getId() {
                return id;
        }

        public String getDeviceId() {
                return deviceId;
        }

        public String getSensorDeviceType() {
                return sensorDeviceType;
        }

        public Long getTimestamp() {
                return timestamp;
        }

        public Double getValue() {
                return value;
        }
}
