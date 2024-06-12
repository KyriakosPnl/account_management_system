#### Να υλοποιήσετε τις παρακάτω REST διεπαφές με τις εξής προδιαγραφές:
1. Ανάκτηση στοιχείων δικαιούχου.   
-->  /api/beneficiaries?page=1&size=10 : endpoint that returns paginated results (all beneficiaries data accessible)
-->  /api/beneficiaries/{beneficiaryId} : endpoint that returns a beneficiary based on beneficiary_id 

2. Ανάκτηση των λογαριασμών ενός δικαιούχου.
-->  /api/beneficiaries/{beneficiaryId}/accounts : endpoint that returns the accounts of a beneficiary  based on beneficiary_id (

3. Ανάκτηση των συναλλαγών ενός δικαιούχου.
--> /api/beneficiaries/{beneficiaryId}/transactions  : endpoint that returns the transactionss of a beneficiary based on beneficiary_id

4. Ανάκτηση του υπολοίπου των λογαριασμών ενός ανθρώπου.
--> /api/beneficiaries/{beneficiaryId}/balance  : endpoint that returns the balance of the accounts of a beneficiary

5. Ανάκτηση της μεγαλύτερης ανάληψης για έναν δικαιούχο τον τελευταίο μήνα.
--> /api/beneficiaries/{beneficiaryId}/largest-withdrawal-last-month  : endpoint that returns the balance of the accounts of a beneficiary

   #### Demo video: https://youtu.be/HIBgv1hm8P0

   #### DB demonstration video: https://youtu.be/KSIVMGntJFw

## Things I would do if I had more time:
- Give more attention to validation by creating a validator class and using it as a tool
- Include auth functionality since we are handling bank accounts (maybe an overkill)
- Register more evants in the logger
   

#### Project notes:

This project was created using Spring Boot and MySql database. Of course the provided csv files that were imported to their coresponding tables in the database (you can check how here: src/Script.sql ) 

For the needs of the project there were created some packages: models , controllers , repositories , services , utils

## models:
They represent Entities which are bacicaly representing the data in our case. We have three models: Account, Beneficiary and Transaction. All of them contain the necessary variables
with getter and setter methods. There are also some anotations that define the relationships between the models

## Controllers
In our case we use only one Controller class which is responsible for declaring and providing the necessary Endpoints for our API. The controller is basicaly the starting point of providing the api

## repositories:
The repositories are Interfaces that are responsible for handling database operations related to specific entities/models in the application. 

## services: 
The services are responsible for performing the required database actions. They basically fetch the required data from the db using the JPA in our case

## utils
Utils is a generic package that contains classes that help with the functionalities  of the project. In our case it only contains a DTO (Data Transfer Object) class that is responssible for customizing the Api's context and structure.
In our case I only implemented the TransactionDTO, in order to modify the default output for the transactions. Of course there are other ways to perform this kind of operation, but I found this way more intresting

#### The most important dependencies for this project are : 

## Spring Boot Starter Web
**Artifact**: `spring-boot-starter-web`

Spring Boot framework is a well known java MVC framework that is used to create APIS. In the background it uses Spring MVC .

## Spring Boot Starter Data JPA
**Artifact**: `spring-boot-starter-data-jpa`

Adds JPA and Hibernate support. 
JPA (ORM in Java) offers a standardized approach to managing relational data within Java applications. 
Hibernate, on the other hand, is an implementation of JPA. 
It not only fulfills the JPA specification but also extends it with additional features and tools, making it a robust and versatile framework for interacting with databases

## MySQL Connector/J
**Artifact**: `mysql-connector-java`

The MySQL JDBC driver. Required to connect to a MySQL database.

