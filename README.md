# Ali Demo Project
This project is create in Java 8 Spring boot application. This project has following components:
1. Rest Web Services with industry standard design pattern.
2. Logging using Log4j2.
3. Junit IT Test cases.

# Setup Demo Project into IDE

Project Requirements
1. Java 8.
2. Spring Boot supported IDE.
3. Postman.
4. Web Browser.

*Eclipse/STS
Open Eclipse/STS > Go in File > Open project > Maven Import > Import existing maven project.

*IntelliJ IDEA
Open intellij > Go in File > Open > Select directory from file chooser and import as maven project.

#How to Run
Open class ```com.ali.rewardprogram.RewardProgramApplication``` & Run main project.
Server will be start on **port 8080**. Run URL ```http://localhost:8080``` in the browser. **Welcome to ali demo** will print it means server has started successfully.

#Call APIs from Postman

Open Postman and Create a Request.
1. Register a user
```
Endpoint: /user
Method: POST
Header: 
    Content-Type: application/json

Request-Payload:
    {
        "username": "ali",
        "name": "Ali Rahmati"
    }

Response:
{
    "id": 1,
    "username": "ali",
    "name": "Ali Rahmati"
    "registrationDate": "2022-04-01T17:48:52.018+00:00"
}
```

2. Save Purchase & Evaluate Rewards

```
Endpoint: /reward
Method: POST
Header: 
    Content-Type: application/json

Request-Payload:
    {
        "username": "ali",
        "total": 120
    }

Response:
{
    "earned_points": 90
}
```
3. Get Earned Points for a month

```
Endpoint: /reward
Method: GET
Query Params: 
    username: ali
    date=02-2022 (DD-MMMM)
Header: 
    Content-Type: application/json

Response:
{
    "earned_points": 219
}
```

# Run IT JUnit Test Cases.

Open IDE & Open ```com.ali.rewardprogram.api.RewardITTest.java``` and Run this class as Junit Test.