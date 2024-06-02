# Passerby Diary




## About The Project

Passerby Diary is a simple and sophisticated diary application that allows users to create and share their diaries. In addition, it has a "Passerby" section where users can post their diary, which are open to all users. However, passers-by can only view the passerby diaries, and they can't comment or connect with the diary owners. The app is designed to be a safe and private space for users to share their thoughts and experiences with others.



## Install The Project

1. **Start the Docker Daemon:** On **macOS**, you can start Docker by opening the Docker Desktop application. 
2. Run 'docker compose up -d' at 'user-service' service. -> run mysql service, create database.
3. Run 'service-registry' service.
4. Run 'api-gateway' service.
5. Run 'user-service' service. -> create mysql tables
6. At 'user-service' service, run 'resources/data/ *.sql




## Authors and acknowledgment

Author: Mengyun Xie
Date: Jun 02 2024


## License

[MIT](https://choosealicense.com/licenses/mit/)