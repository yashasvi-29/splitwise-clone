
# Splitwise Clone (Expense Sharing App) - Sample Project

This repository contains a basic scaffold for a Splitwise-like application:
- **Backend:** Spring Boot (REST API) with JPA/Hibernate and MySQL
- **Frontend:** React.js (simple components)
- **Auth:** JWT + Spring Security (skeleton implementation)
- **Database:** MySQL schema sample

> This is a starter project scaffold. It includes working-style code and placeholders so you can extend features:
- expense splitting logic
- settlement and balance calculations
- user management UX polish

## What's included
- `backend/` - Spring Boot application (Java)
- `frontend/` - React app (JavaScript)
- `Postman_collection.json` - example API calls
- `sql/schema.sql` - basic schema and sample data
- `README.md` - this file

---

## How to run (step-by-step)

### 1) Setup MySQL
1. Install MySQL and create a database, for example:
   ```sql
   CREATE DATABASE splitwise_db;
   ```

2. Update `backend/src/main/resources/application.properties` with your DB username/password and JWT secret.

### 2) Run backend (Spring Boot)
1. Make sure you have Java 17+ and Maven installed.
2. From `backend/` run:
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```
   Or run the generated jar:
   ```bash
   java -jar target/splitwise-backend-0.0.1-SNAPSHOT.jar
   ```

Default backend runs on `http://localhost:8080`.

### 3) Run frontend (React)
1. From `frontend/`:
   ```bash
   npm install
   npm start
   ```
2. App will open on `http://localhost:3000` and call the backend.

### 4) Test with Postman
Import `Postman_collection.json` to test the endpoints:
- `POST /api/auth/signup`
- `POST /api/auth/login`
- `GET /api/groups`
- `POST /api/groups`
- `POST /api/expenses`

---

## Project highlights & architecture notes

- **MVC**: Controllers handle API requests, Services contain business logic (skeleton), Repositories use Spring Data JPA.
- **Entities**: `User`, `ExpenseGroup`, `Expense`, `ExpenseShare` model relationships. Groups have many users, expenses belong to groups.
- **Expense splitting strategy**: Each `Expense` stores `ExpenseShare` entries per participant with their owed amounts; balancing and settlement logic can be implemented in `ExpenseService`.
- **Security**: JWT-based, with filters and utilities included as a starting point.
- **Frontend**: React with axios; simple components show flow: login -> dashboard -> group view -> add expense.

---

## Next steps & extensions you might add
- Real-time updates using WebSockets
- More complete settlement engine (minimize transactions)
- Push notifications / email invites
- Improve frontend UX and error handling
- Pagination, sorting, and full validation for all endpoints

---

## License
This project scaffold is provided as-is for learning and prototyping.
