<div style="display: flex">
    <div style="float: left">
        <h1>
            <br>
            <a href="https://www.adultswim.com/streams/rick-and-morty">
                <img src="https://www.overlyanimated.com/wp-content/uploads/2018/02/unnamed2.png" style="height: 200px; margin: 10px auto 20px; display: block;">
            </a>
        </h1>
    </div>
</div>
<div>
    <h2 align="center">Simple mini-app for fans of famous sitcom "Rick and Morty", <br> which is using public API to get info about all characters</h2>
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
* Docker
# Quick start
1) Clone this repository
2) Download image with Postgres 15-Alpine from Docker ```docker pull postgres:15-alpine```
3) Download image with Project Rick and Morty from Docker ```docker pull octopy/rick-and-morty:latest```
4) Run project with command ```docker-compose up```
