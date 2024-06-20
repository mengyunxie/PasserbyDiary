# Passerby Diary




## About The Project

Passerby Diary is a simple and sophisticated diary application that allows users to create and share their diaries. In addition, it has a "Passerby" section where users can post their diary, which are open to all users. However, passers-by can only view the passerby diaries, and they can't comment or connect with the diary owners. The app is designed to be a safe and private space for users to share their thoughts and experiences with others.



## Install The Project

1. Start the Docker Daemon: On macOS, you can start Docker by opening the Docker Desktop application.
2. Run 'docker compose up -d' at  'user-service' service. -> run mysql service, create database.
3. Login 'https://cloud.mongodb.com/', if don't have a database for the project, then create one, use the link to let the local compass connect the cloud. Then update the link into 'application.properties.
4. Zipkin: run: 'docker run -d -p 9411:9411 openzipkin/zipkin'
5. Run 'service-registry' service.
6. Run 'api-gateway' service.
7. Run 'user-service' service.  -> create mysql tables
8. At 'user-service' service, run 'resources/data/ *.sql





http://localhost:8761/
http://localhost:9411/zipkin/


## Authors and acknowledgment

Author: Mengyun Xie
Date: Jun 02 2024


## License

[MIT](https://choosealicense.com/licenses/mit/)