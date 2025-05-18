# 💳 Application E-Banking — Backend

A Spring Boot application for managing banking operations, including client management, bank accounts, and debit/credit transactions.

## Overview

This backend provides a RESTful API for a banking application with the following capabilities:
- Client and account management.
- Handling banking operations (credit and debit).
- Secure authentication using JWT.
- Recording the user who performed each operation.
- Administration of user accounts.

## Main Features

- Create and manage clients.
- Current accounts with authorized overdraft.
- Savings accounts with interest rate.
- Record banking operations (credit, debit).
- Transaction history per account.
- Secure access with Spring Security + JWT.
- API documentation with Swagger/OpenAPI.

## Project Architecture

```text
src/
├── EbankingApplication.java
├── dtos/
│   ├── AccountHistoryDTO
│   ├── AccountOperationDTO
│   └── BankAccountDTO
│   ├── CreditDTO
│   ├── CurrentBankAccountDTO
│   ├── CustomerDTO
│   ├── DebitDTO
│   ├── SavingBankAccountDTO
│   └── TransferRequestDTO
├── entities/
│   ├── Customer.java
│   ├── BankAccount.java (abstract)
│   ├── SavingAccount.java
│   ├── CurrentAccount.java
│   └── AccountOperation.java
├── enums/
│   ├── AccountStatus.java
│   └── OperationType.java
├── exceptions/
│   ├── BalanceNotSufficientException
│   ├── BankAccountNotFoundException
│   └── CustomerNotFoundException
├── mappers/
│   └── BankAccountMapperImpl
├── repositories/
│   ├── CustomerRepository.java
│   ├── BankAccountRepository.java
│   └── AccountOperationRepository.java
├── security/
│   ├── SecurityConfig
│   └── SecurityController
├── services/
│   ├── BankAccountService
│   └── BankAccountServiceImpl
├── web/
│   ├── CustomerRestController.java
│   └── BankAccountRestController.java

```

## Entity Relationships

- **One client** can have **multiple bank accounts** (OneToMany).
- **One bank account** can have **multiple operations** (OneToMany).
- `BankAccount` is an abstract class:
  - `CurrentAccount` adds the `overDraft` field.
  - `SavingAccount` adds the `interestRate` field.
  

## Class Diagram

Below is the class diagram representing the main entities of the application:

![image](https://github.com/user-attachments/assets/f687256a-dd7a-48fb-8979-de9cbca5aa12)


## Database Schema (H2)

- **Customer**: id, name, email  
- **BankAccount**: id, balance, creationDate, status, customer_id  
- **CurrentAccount**: overDraft  
- **SavingAccount**: interestRate  
- **AccountOperation**: id, date, type, amount, description, bankAccount_id, userId

## Security

- Authentication via Spring Security and JWT.
- User roles: `ADMIN`, `USER`.
- Account and password management system.
- Users can change their password.

## Important Maven Dependencies

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--<dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>-->

        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.36</version>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
            <version>3.4.5</version>
        </dependency>
        <!-- Pour Spring Boot 3+ -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.1.0</version>
        </dependency>
    </dependencies>
```

## Frontend Link

The frontend for this application is available here:  https://github.com/amine-ghazoui/ebanking-front
