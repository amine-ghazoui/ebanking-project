# 💳 Application E-Banking — Backend

Une application Spring Boot pour la gestion des opérations bancaires, y compris la gestion des clients, des comptes bancaires, et des opérations de débit/crédit.

## 📝 Présentation

Ce backend fournit une API RESTful pour une application bancaire permettant :
- La gestion des clients et de leurs comptes.
- La gestion des opérations bancaires (crédit, débit).
- L’authentification sécurisée avec JWT.
- L’enregistrement de l’utilisateur ayant effectué chaque opération.
- L’administration des comptes utilisateurs.

## 🎯 Fonctionnalités principales

- Création et gestion des clients.
- Comptes courants avec découvert autorisé.
- Comptes épargne avec taux d’intérêt.
- Enregistrement des opérations (crédit, débit).
- Historique des opérations par compte.
- Sécurité avec Spring Security + JWT.
- Documentation API avec Swagger/OpenAPI.

## 🧱 Architecture du projet

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
│   ├── BankAccount.java (abstraite)
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
│   ├── BankAccountMapperImpl
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
│
├── web/
│   ├── CustomerRestController.java
│   ├── BankAccountRestController.java
└──
```


## 🧩 Relations entre les entités

- **Un client** possède **plusieurs comptes bancaires** (OneToMany).
- **Un compte bancaire** a **plusieurs opérations** (OneToMany).
- **BankAccount** est une classe abstraite :
  - `CurrentAccount` : ajoute un champ `overDraft`.
  - `SavingAccount` : ajoute un champ `interestRate`.

## 🛢️ Schéma de la base de données (H2)

- **Customer** : id, nom, email
- **BankAccount** : id, balance, date de création, status, customer_id
- **CurrentAccount** : overDraft
- **SavingAccount** : interestRate
- **AccountOperation** : id, date, type, montant, description, bankAccount_id, userId

## 🔐 Sécurité

- Authentification via Spring Security avec JWT
- Utilisateurs avec rôles : ADMIN, USER
- Système de gestion de comptes et mot de passe
- Changement de mot de passe par l'utilisateur

## 📦 Dépendances Maven importantes

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

        <!--<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
            <version>3.4.5</version>
        </dependency>-->
        <!-- Pour Spring Boot 3+ -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.1.0</version>
        </dependency>
    </dependencies>
```
