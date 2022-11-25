<div style="display: flex">
    <div style="float: left">
        <h1>
            <a href="https://www.adultswim.com/streams/rick-and-morty">
                <img src="https://www.overlyanimated.com/wp-content/uploads/2018/02/unnamed2.png" style="height: 200px; margin: 10px auto 20px; display: block;">
            </a>
        </h1>
    </div>
</div>
<div>
    <h2 align="center">Simple online mini-app for fans of famous sitcom "Rick and Morty", <br> which is using public API to get info about all characters</h2>
</div>

# Functionality
* Send GET requests to API through Swagger
* Fetch data from API and save to your DB
* With [CronJob](https://cron.com/) API will send request to fetch/synchronize data every day at 8 AM
# App Structure
Based on N-Tier architecture:
1. Layer of Controllers.
2. Layer of Service.
3. Layer of Repository.
# Technologies
* Java 11
* Tomcat 9.0.65
* Maven
* Spring Boot 2.7.5
* Mockito
* Cron  
* Docker
# Quick start
1) Clone this repository
2) Download image with Project Rick and Morty from Docker ```docker pull octopy/rick-and-morty:latest```
3) Set up necessary fields in ```application.properties```
```
spring.datasource.url=DB_URL
spring.datasource.username=USER
spring.datasource.password=PASSWORD
spring.jpa.properties.hibernate.dialect=DIALECT
```
4) Start maven compiling code and packaging it to jar - run **mvn clean package**
5) Run project with command ```docker-compose up```
___

# How to send GET methods
* Open web-browser and write down ```http://localhost:8080/swagger-ui/#``` to open Swagger
* or
* Write into URL:
```
    http://localhost:8080/movie-characters/random
    http://localhost:8080/movie-characters/by-name?name=Scar Rick
```