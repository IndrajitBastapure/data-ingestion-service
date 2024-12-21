# Read Me First

**Data-Ingestion-Service API**

* Consume messages from Kafka, process and store in DB.

# Prerequisite - Environment Setup
1. JRE 17
2. Maven 4.0+
3. IDE of your choice
4. Docker Desktop
# Prerequisite - Dependency on another applications
* Data-Ingestion-Service API is dependent on
    1. iot-simulator-service

# Getting Started - Build & Run Application
* Execute command - docker compose up -d to start timescale db container locally on Docker Desktop
* Verify if the timescale db container is properly started with command - docker ps
* Build application with command - mvn clean install
* Start iot-simulator-service application with command - mvn spring-boot:run
* Verify if the application started smoothly on port 8082
* Check if application is able to consume the records from Kafka topic and save in the Timescale Db successfully.
* Goto docker desktop terminal and execute command to connect to timescale db locally - docker exec -it timescaledb psql -U admin -d iot_data
* Check if records are persisted in the Timescale DB by executing query -Select * from iot_data;