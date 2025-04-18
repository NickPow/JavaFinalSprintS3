# Gym Management System

A console-based Java application for managing gym operations with PostgreSQL.

## Features

- User registration and secure login with BCrypt
- Role-based access for Admin, Trainer, and Member
- Membership purchase and revenue tracking
- Workout class management by trainers
- Expense tracking for members
- PostgreSQL integration via JDBC
- Clean, menu-driven console UI with input validation

## User Roles

### Admin
- View all users with contact info
- Delete users by ID
- View all memberships
- View total revenue from memberships

###  Trainer
- Add, update, delete workout classes
- View their assigned classes
- Purchase their own gym membership

### Member
- Browse available workout classes
- Purchase gym memberships
- View total membership expenses

## Technologies

- Java 17+
- PostgreSQL
- Maven
- jBCrypt (password hashing)
- JDBC


## Dependencies Used

- **PostgreSQL JDBC Driver** – connects Java to PostgreSQL
- **jBCrypt** – securely hashes and verifies passwords
- **Maven Exec Plugin** – runs the `Main` class from the terminal

## Test Accounts

| Role    | Username  | Password     |
|---------|-----------|--------------|
| Admin   | admin1    | adminpass    |
| Trainer | trainer1  | trainerpass  |
| Member  | member1   | memberpass   |

## Instructions to run

- Clone the repository. 
- Set up the database in PostgreSQL:
  
    CREATE USER gymuser WITH PASSWORD 'gympass';
  
    CREATE DATABASE gymdb;
  
    GRANT ALL PRIVILEGES ON DATABASE gymdb TO gymuser;
  
- Run:
  
    psql -U gymuser -d gymdb -f schema.sql
  
    psql -U gymuser -d gymdb -f seed.sql
  
- Run the app:
  
    mvn clean compile
  
    mvn exec:java -Dexec.mainClass="com.gym.Main"


## Documentation

Extra documentation can be found in the `docs/` folder






