# Thoughtstream Project - Microservices

## Overview
The Thoughtstream Project is a sophisticated diary application designed to provide a safe and private space for users to create and share their diaries. This project features a microservices architecture built with Java, Spring Boot, and various supporting technologies to ensure scalability, performance, and security.

### Key Features

- **Microservices Architecture**: Each functionality is encapsulated in its own service, promoting modular development and independent deployment.
- **Real-Time Data Processing**: Utilizes Kafka for efficient real-time data streaming and processing.
- **Secure User Authentication**: Implements Spring Security and JWT for user authentication and authorization.
- **Database Integration**: Integrates MySQL and MongoDB for optimized data storage and retrieval.

## Project Structure

The backend is organized into several microservices, each with its own responsibilities:

passerby-service/
├── api-gateway         # Gateway for routing requests to appropriate services
├── auth-service        # Manages user authentication and authorization
├── diary-service       # Handles diary creation, retrieval, and management
├── service-registry    # Eureka service registry for service discovery
└── user-service        # Manages user profiles and data

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

### Contributing

Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.

## Authors and acknowledgment

Author: Mengyun Xie
Date: Jun 02 2024


## License

[MIT](https://choosealicense.com/licenses/mit/)
