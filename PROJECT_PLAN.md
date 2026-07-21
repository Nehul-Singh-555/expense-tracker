DONE IS BETTER THAN PERFECT!
# Expense Tracker

## 1. Project Overview
A backend application that allows users to manage their income and expenses through REST APIs. The application stores financial records in a MySQL database and provides CRUD operations.

## 2. Objectives
- Learn Spring Boot
- Learn REST APIs
- Learn Spring Data JPA
- Learn MySQL integration
- Practice Git and GitHub
- Build a resume-worthy backend project

## 3. Features (MVP)
- Add an expense
- View all expenses
- View a single expense
- Update an expense
- Delete an expense

## 4. Future Enhancements
- Categories
- Income tracking
- Monthly summaries
- Search and filtering
- Budget limits
- User authentication (JWT)
- Charts (when a frontend is added)
- Deployment on Azure

## 5. Tech Stack

Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven

Database
- MySQL

Testing
- Postman

Version Control
- Git
- GitHub

IDE
- VS Code

Deployment (Later)
- Azure

## 6. Database Design

Expense
- id
- title
- amount
- category
- date
- type
- notes

## 7. API Endpoints

POST   /expenses
GET    /expenses
GET    /expenses/{id}
PUT    /expenses/{id}
DELETE /expenses/{id}

## 8. Milestones

Phase 1
- Create Spring Boot project
- Connect MySQL
- Create Expense entity

Phase 2
- Implement CRUD APIs
- Test using Postman

Phase 3
- Improve validation
- Exception handling

Phase 4
- Deploy to Azure

## 9. Learning Goals
- Understand Spring Boot architecture
- Learn JPA and database relationships
- Improve Java skills
- Learn REST API design
- Gain experience using Git in a real project

-----------------------------------------------------------------------------------------------

# Expense Tracker API

## Expense

- [ ] Create Expense
- [ ] Get All Expenses
- [ ] Get Expense by ID
- [ ] Update Expense
- [ ] Delete Expense