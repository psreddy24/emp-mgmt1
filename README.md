# Employee Management Service API

> **A REST-ful API to handle employee\people back-end operations**

![Platform](https://img.shields.io/static/v1?label=Platform&message=Java%208%20|%20JEE%208&color=lightgreen)
![Framework](https://img.shields.io/static/v1?label=Framework&message=Spring%205&color=lightgreen)
![Runtime](https://img.shields.io/static/v1?label=Runtime&message=Spring%20boot&color=lightgreen)


## Table of Contents

> Detailed information can be found below in the following sections 

- [Pre requisites](#pre-requisites)
- [How to run locally?](#how-to-run-locally)
    - [Using local installation](#using-local-installation)
    - [Running in container](#using-docker)
    - [Running unit\BDD tests for backend](#running-backend-test)
    - [Running keram tests for frontend](#running-frontend-test)
- [About Project](#about)
    - [Description](#description)
    - [Objective](#objective)
    - [Consumers](#consumers)
    - [Tech & Specifications](#technologies--specifications)
    - [General Validations](#validation)
- [Resources](#resources)
    - [Documentation](#documentation)
- [Integration Tests](#integration-tests-postman)
    - [How to run integration tests](#how-to-run-integration-tests)

---
## Pre requisites
- JDK 1.8+
- Maven 3+
- Docker runtime

[Back to TOC](#table-of-contents)

---
## How to run locally?

- Clone the repository and change directory as below
    ```
    > git clone https://github.com/psreddy24/emp-mgmt1.git
    > cd emp-mgmt
    > git checkout master
    ```

#### Using local installation
- Run the following command to run the back-end API
  ```
  > cd emp-mgmt-backend
  > mvn clean package
  ```
- Run the following command to run the back-end API
  ```
  > cd emp-mgmt-frontend
  > npm set audit false
  > npm install
  > npm install bootstrap@3.3.7 --save
  > run npm install --save-dev @angular-devkit/build-angular@0.901.10
  > ng serve
  ```
- Verify the installation by accessing the app on http://localhost:4200

#### Using docker

- Run the following commands
    ```
    > cd emp-mgmt-backend
    > mvn clean package
    > cd ..
    > docker-compose up
    ```
- Verify the installation by accessing the app on http://localhost:8080

[Back to TOC](#table-of-contents)

#### Running backend test

- Run the following commands
    ```
    > cd emp-mgmt-backend
    > mvn test
    ```

[Back to TOC](#table-of-contents)

#### Running frontend test

- Run the following commands after building frontend as mentioned [above](#using-local-installation)
    ```
    > cd emp-mgmt-frontend
    > ng test
    ```

[Back to TOC](#table-of-contents)

---
## About
The details about the project and infrastructure can be found here

### Description

It's an REST-ful API, which provides the capability to CREATE, RETRIEVE, SEARCH, and UPDATE employee\people

[Back to TOC](#table-of-contents)

### Objective
The objective of the system is to provide an integration point for the front-end application 

[Back to TOC](#table-of-contents)

### Consumers
- emp-mgmt-frontend

[Back to TOC](#table-of-contents)

### Technologies & Specifications

- Language: Java 1.8
- Java EE: Jee 8
- Runtime: Tomcat
- Spring 5
- H2 in memory database

[Back to TOC](#table-of-contents)

---
## Resources

#### Documentation
- [API Contract(Swagger UI)](https://techtestapi.azurewebsites.net/swagger/index.html)
    
[Back to TOC](#table-of-contents)
