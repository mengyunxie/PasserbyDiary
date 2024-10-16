# Passerby Diary Project - Microservices

## Overview
The Passerby Diary Project is a sophisticated diary application designed to provide a safe and private space for users to create and share their diaries. This project features a microservices architecture built with Java, Spring Boot, and various supporting technologies to ensure scalability, performance, and security.

### Key Features

- **Microservices Architecture**: Each functionality is encapsulated in its own service, promoting modular development and independent deployment.
- **Real-Time Data Processing**: Utilizes Kafka for efficient real-time data streaming and processing.
- **Secure User Authentication**: Implements Spring Security and JWT for user authentication and authorization.
- **Database Integration**: Integrates MySQL and MongoDB for optimized data storage and retrieval.

## Project Structure

The backend is organized into several microservices, each with its own responsibilities:

```
passerby-service/
├── api-gateway         # Gateway for routing requests to appropriate services
├── auth-service        # Manages user authentication and authorization
├── diary-service       # Handles diary creation, retrieval, and management
├── service-registry    # Eureka service registry for service discovery
└── user-service        # Manages user profiles and data
```

## Technologies Used

- **Java**: The primary programming language for developing services.
- **Spring Boot**: Framework for building microservices.
- **Spring Cloud**: For managing configuration and service discovery.
- **Netflix Eureka**: For service registration and discovery.
- **MySQL**: For relational data storage.
- **MongoDB**: For document-based data storage.
- **Kafka**: For messaging and real-time data streaming.
- **Docker**: For containerization and deployment.
- **Spring Security & JWT**: For secure user authentication.

## Getting Started

### Prerequisites

Make sure you have the following installed:

- [Java 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/get-started)
- [MySQL](https://www.mysql.com/)
- [MongoDB](https://www.mongodb.com/try/download/community)
- [Kafka](https://kafka.apache.org/downloads)

Here’s an updated section for setting up the backend, formatted in Markdown. This section can be added to the combined README file.

### Setup

1.	Start the Docker Daemon:
	•	On macOS, you can start Docker by opening the Docker Desktop application.

2.	Run the MySQL Service:
	•	Navigate to the user-service directory in your terminal and run:
```
docker compose up -d
```
	•	This command will start the MySQL service and create the necessary database.

3.	Set Up MongoDB:
	•	Log in to MongoDB Cloud.
	•	If you don’t have a database for the project, create one.
	•	Use the connection link provided to connect to your database using MongoDB Compass.
	•	Update the MongoDB connection link in application.properties within your back-end services.

4.	Run Zipkin for Distributed Tracing:
	•	Start Zipkin by running the following command in your terminal:
```
docker run -d -p 9411:9411 openzipkin/zipkin
```

5.	Run the Service Registry:
	•	Navigate to the service-registry directory and start the service:
```
mvn spring-boot:run
```

6.	Run the API Gateway:
	•	Navigate to the api-gateway directory and start the service:
```
mvn spring-boot:run
```

7.	Run the User Service:
	•	Navigate to the user-service directory and start the service:
```
mvn spring-boot:run
```
	•	This step will create the necessary MySQL tables.

8.	Initialize the Database:
	•	In the user-service directory, run the SQL scripts located in the resources/data folder to populate the database with initial data:
```
mysql -u your_username -p your_database_name < resources/data/*.sql
```
	•	Replace your_username and your_database_name with your MySQL credentials and database name.

Accessing Services
	•	Once all services are running, you can access:
	•	The Service Registry at: http://localhost:8761/
	•	Zipkin at: http://localhost:9411/zipkin/


## Contributing

Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.

## Authors and acknowledgment

Author: Mengyun Xie
Date: Jun 02 2024


## License

[MIT](https://choosealicense.com/licenses/mit/)
