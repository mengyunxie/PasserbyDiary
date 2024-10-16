# Passerby Diary

## Overview

Passerby Diary is a sophisticated diary application that allows users to create and share their diaries. It features a "Passerby" section where users can post diaries that are open to all users. Passers-by can view these diaries but cannot comment or connect with the diary owners. The app is designed to be a safe and private space for users to share their thoughts and experiences.

## Built With

### Front-End
- [React](https://reactjs.org/)
- [Express](https://expressjs.com/)
- [Cookie-parser](https://www.npmjs.com/package/cookie-parser)
- [UUID](https://www.npmjs.com/package/uuid)

### Back-End
- [Java](https://www.oracle.com/java/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [Netflix Eureka](https://github.com/Netflix/eureka)
- [MySQL](https://www.mysql.com/)
- [MongoDB](https://www.mongodb.com/)
- [Kafka](https://kafka.apache.org/)
- [Docker](https://www.docker.com/)

## Project Structure

The backend is organized into several microservices:
```
passerby-service/
├── api-gateway         # Gateway for routing requests to appropriate services
├── auth-service        # Manages user authentication and authorization
├── diary-service       # Handles diary creation, retrieval, and management
├── service-registry    # Eureka service registry for service discovery
└── user-service        # Manages user profiles and data
```

## Prerequisites

Make sure you have the following installed:

- [Node.js](https://nodejs.org/)
- [npm](https://www.npmjs.com/)
- [Java 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/get-started)
- [MySQL](https://www.mysql.com/)
- [MongoDB](https://www.mongodb.com/try/download/community)
- [Kafka](https://kafka.apache.org/downloads)


## Visuals

<img src="./passerby-web/visuals.png" alt="visuals" style="zoom:50%;" />

Passerby Diary is designed to be intuitive and user-friendly, with a simple and clean interface that makes it easy to navigate and use.

## Usage

To use Passerby Diary, follow these simple steps:
	1.	Create a new account or log in to an existing one.
	2.	In the “Passerby” section, view the list of passerby diaries and click on each diary to view the details.
	3.	In the “Mydiary” section, click “New Diary” in the upper right corner to start creating your diary. If you check “Post to Passerby,” this will share your thoughts with other users in the “Passerby” section.
	4.	In the “Mydiary” section, view the list of all your diaries. Click on each diary to view its details, and edit or delete it.
	5.	In the “Setting” section, view your profile and update your avatar.


## Contributing

Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.


## Authors and acknowledgment

Author: Mengyun Xie
Date: Apr 17 2023


## License

[MIT](https://choosealicense.com/licenses/mit/)
