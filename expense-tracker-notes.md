# Expense Tracker — Developer Notes
## Session 1: Project Setup & Spring Boot Fundamentals

---

## 1. Spring Boot

- A Java framework used to build backend applications quickly.
- Reduces boilerplate configuration compared to the older Spring Framework.
- Comes with an embedded web server (Tomcat by default) — no external server installation needed.

---

## 2. Maven

**Purpose:**
- Manages project dependencies
- Builds the project
- Runs tests
- Packages the application

**`pom.xml`**
- The heart of a Maven project.
- Stores project information and dependencies.

**Example dependencies:**

| Dependency | Purpose |
|---|---|
| Spring Web | Build REST APIs |
| Spring Data JPA | Communicate with relational databases |
| MySQL Driver | Connect to MySQL database |

---

## 3. Spring Initializr

**Purpose:**
- Generates the initial Spring Boot project structure
- Adds selected dependencies automatically
- Saves time by creating a ready-to-use project

---

## 4. Project Structure

```
expense-tracker/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   │
│   └── test/
│
├── pom.xml
└── mvnw
```

| Path | Contents |
|---|---|
| `src/main/java` | All Java source code |
| `src/main/resources` | Configuration files (e.g. `application.properties`) |
| `src/test` | Test classes |

---

## 5. Package Organization

Instead of placing every class together (`Expense.java`, `ExpenseController.java`, `ExpenseRepository.java`, ...), organize into packages:

```
controller/
service/
repository/
entity/
dto/
config/
exception/
```

**Why?**
- Easier maintenance
- Easier debugging
- Better organization
- Easier collaboration
- Scales well for large projects

---

## 6. Git

`git init`
- Initializes a Git repository.
- Creates a hidden `.git` folder.

The `.git` folder stores:
- Commit history
- Branches
- Git configuration

> Without `.git`, Git commands won't work.

---

## 7. Entity

An **Entity** represents a table in the database.

```
Expense.java  →  Expense Table
```

Each object corresponds to one row.

**Example:**

| id | title | amount |
|---|---|---|
| 1 | Pizza | 350 |

---

## 8. Choosing Data Types

Java types are chosen based on the data they represent — **not** the SQL type.

| Data | Java Type |
|---|---|
| Title | `String` |
| Amount | `BigDecimal` |
| Date | `LocalDate` |
| Notes | `String` |
| ID | `Long` |

> **Important:** Don't think in SQL types while writing Java.
> ❌ `VARCHAR` → ✅ `String`
>
> JPA maps Java types to SQL types automatically.

---

## 9. Why `BigDecimal`?

Money should not usually be stored as `double`.

**Example:**
```
0.1 + 0.2  →  0.30000000000000004
```

`BigDecimal` avoids floating-point precision issues.

---

## 10. Why `LocalDate`?

Instead of storing dates as strings (`"2026-07-13"`), use `LocalDate`.

**Advantages:**
- Sorting
- Comparing dates
- Adding days
- Better validation

---

## 11. Primary Key

Every table needs a unique identifier.

```java
@Id
private Long id;
```

**Purpose:**
- Uniquely identify each row
- Avoid ambiguity

---

## 12. Generated IDs

Users should not enter IDs manually. Instead, let the database generate them:

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

**Example output:** `1, 2, 3, 4 ...`

---

## 13. Why `Long` instead of `long`?

`Long` can be `null`.

```java
Expense expense = new Expense();
// ID does not exist yet

repository.save(expense);
// Database generates the ID
```

A primitive `long` cannot represent "no value yet" — `Long` (the wrapper class) can.

---

## 14. Encapsulation

Fields should usually be `private`, not `public`.

**Reason:** Prevent uncontrolled modification. Access should happen through methods (getters/setters).

---

## 15. Enum

Use `Enum` when only a fixed set of values is allowed.

```java
enum TransactionType {
    INCOME,
    EXPENSE
}
```

Prefer `TransactionType type;` over a raw `String type;`.

---

## 16. Layered Architecture

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
```

| Layer | Responsibility |
|---|---|
| Controller | Receives HTTP requests |
| Service | Contains business logic |
| Repository | Communicates with the database |
| Database | Stores data permanently |

---

## 17. Single Responsibility Principle (SRP)

Every class should have **one** responsibility.

**Example:** `ExpenseController` should only handle expense-related requests — don't create one huge controller for everything.

---

## 18. How to Learn Like a Developer

For every new concept:
1. Identify the problem
2. Think of a solution
3. Learn the theory
4. Read the documentation
5. Write the code
6. Test it
7. Refactor if needed

**Questions to always ask yourself before writing code:**
- What problem am I solving?
- Which layer should handle this?
- Does this class have only one responsibility?
- Is this Java logic or database logic?
- Can this design scale if the project grows?

# Session 2 - REST APIs & HTTP

## What is an API?

API stands for **Application Programming Interface**.

An API is a **contract** that allows two software applications to communicate with each other.

Example:

```text
React Frontend
        │
 HTTP Request
        ▼
Spring Boot Backend
        │
 HTTP Response
        ▼
React Frontend
```

The frontend never communicates directly with the database.

It always communicates with the backend through an API.

---

## HTTP Request

A request sent by the client to the server asking it to perform an action.

Examples:

- Create an expense
- Get all expenses
- Update an expense
- Delete an expense

---

## HTTP Response

The response sent back by the server after processing the request.

Example:

Client:

```
GET /expenses
```

Server:

```json
[
  {
    "id": 1,
    "title": "Pizza",
    "amount": 350
  }
]
```

---

## CRUD Operations

CRUD stands for:

- Create
- Read
- Update
- Delete

Almost every backend application performs these operations.

---

## HTTP Methods

| HTTP Method | Purpose | Example |
|-------------|---------|---------|
| GET | Read data | Get all expenses |
| POST | Create data | Add a new expense |
| PUT | Update existing data | Change an expense |
| DELETE | Delete data | Remove an expense |

---

## CRUD ↔ HTTP Mapping

| CRUD | HTTP |
|------|------|
| Create | POST |
| Read | GET |
| Update | PUT |
| Delete | DELETE |

---

## Real-World Examples

### Instagram Feed

Opening Instagram:

```
GET /feed
```

Reason:

The client is requesting data.

---

### Expense Tracker

Create a new expense:

```
POST /expenses
```

Get all expenses:

```
GET /expenses
```

Update an expense:

```
PUT /expenses/{id}
```

Delete an expense:

```
DELETE /expenses/{id}
```

---

## API Design

When deciding which HTTP method to use, ask:

- Am I creating a new resource?
- Am I reading existing data?
- Am I updating existing data?
- Am I deleting existing data?

Choose the HTTP method based on the action being performed.

---

## Key Takeaways

- The frontend never talks directly to the database.
- Every request first reaches the backend.
- APIs define how applications communicate.
- CRUD operations map naturally to HTTP methods.
- Think about the action before choosing the HTTP method.

# Session 3 - REST Resource Design

## REST Resources

REST APIs are designed around **resources**, not actions.

Examples of resources:

- users
- expenses
- products
- orders

Avoid action-based URLs like:

```text
/addExpense
/deleteExpense
/updateExpense
```

Instead, use resource-based URLs:

```text
/expenses
/users
/products
```

The HTTP method tells the server what action to perform.

---

## REST Naming Convention

Use **plural nouns** for resources.

Good:

```text
/users
/expenses
/products
```

Avoid:

```text
/user
/expense
/product
```

---

## CRUD Endpoints

### Create

```http
POST /expenses
```

Creates a new expense.

---

### Read All

```http
GET /expenses
```

Returns all expenses.

---

### Read One

```http
GET /expenses/{id}
```

Returns one specific expense.

Example:

```http
GET /expenses/5
```

---

### Update

```http
PUT /expenses/{id}
```

Updates an existing expense.

Example:

```http
PUT /expenses/5
```

---

### Delete

```http
DELETE /expenses/{id}
```

Deletes an expense.

Example:

```http
DELETE /expenses/5
```

---

## Why include the ID?

Without an ID:

```http
PUT /expenses
```

The server doesn't know which expense to update.

With an ID:

```http
PUT /expenses/5
```

The server knows exactly which expense is being modified.

---

## REST Design Principle

Think in terms of:

- Resources (expenses, users, products)
- HTTP Methods (GET, POST, PUT, DELETE)

Avoid putting actions in the URL.

The HTTP method already describes the action.

### @RestController

Problem it solves:
Spring needs a way to identify which classes handle HTTP requests.

Where it is used:
On a class.

What it tells Spring:
This class is a REST controller and should handle web requests.

Example:

@RestController
public class ExpenseController {

}

# Session 5 - Mapping Requests to Methods

## Why is `@RestController` not enough?

`@RestController` tells Spring:

> "This class handles HTTP requests."

However, it does **not** tell Spring which method should handle which request.

Example:

```java
@RestController
public class ExpenseController {

    public void getExpenses() {}

    public void addExpense() {}
}
```

Spring knows this is a controller, but it cannot decide:

- Which method should handle `GET /expenses`?
- Which method should handle `POST /expenses`?

---

## Method Mapping

Method-level annotations provide the missing information.

Example:

```java
@GetMapping("/expenses")
public void getExpenses() {
}

@PostMapping("/expenses")
public void addExpense() {
}
```

Now Spring knows:

- `GET /expenses` → `getExpenses()`
- `POST /expenses` → `addExpense()`

---

## Key Idea

There are two levels of information:

1. **Class-level annotation**
   - Identifies the class as a controller.

2. **Method-level annotation**
   - Maps a specific HTTP method and URL to a specific Java method.

Both are required to build a REST API.

# Session 6 - Controllers

## What is a Controller?

A controller is the entry point of a Spring Boot application.

Its responsibility is to:

- Receive HTTP requests.
- Call the appropriate service.
- Return an HTTP response.

The controller should **not** contain business logic or database code.

---

## @RestController

**Problem it solves**

Spring needs to know which classes should handle HTTP requests.

**Where it is used**

On a class.

**What it tells Spring**

This class is a REST controller and should receive web requests.

---

## Responsibilities of a Controller

- Accept client requests.
- Delegate work to the service layer.
- Return responses to the client.

Avoid placing business logic directly inside the controller.

# Session 7 - First Controller

## @RestController

Marks a class as a REST controller.

Spring scans this class for HTTP endpoints.

---

## @GetMapping

Maps an HTTP GET request to a Java method.

Example:

```java
@GetMapping("/expenses")
public String viewExpenses() {
    return "Expense API is working!";
}
```

---

## @PostMapping

Maps an HTTP POST request to a Java method.

Example:

```java
@PostMapping("/expenses")
public String addExpense() {
    return "Expense added!";
}
```

---

## @RequestMapping

Used at the class level to define a common URL prefix.

Instead of:

```java
@GetMapping("/expenses")
@PostMapping("/expenses")
```

Use:

```java
@RequestMapping("/expenses")
public class ExpenseController {

    @GetMapping
    ...

    @PostMapping
    ...
}
```

This avoids repeating the same URL for every method.

---

## Request Flow

Client

↓

HTTP Request

↓

ExpenseController

↓

Controller Method

↓

HTTP Response

↓

Client

## application.yaml

`application.yaml` is Spring Boot's main configuration file.

It stores application settings such as:

- Database configuration
- Server port
- Logging
- Profiles
- JPA settings

Spring Boot automatically reads this file when the application starts.

# Session 8 - Database Configuration

## What is `application.yaml`?

`application.yaml` is Spring Boot's main configuration file.

Spring Boot reads this file automatically when the application starts.

It is commonly used to configure:

- Application name
- Database connection
- Server port
- JPA settings
- Logging
- Profiles

---

## What is a DataSource?

A DataSource represents the application's connection to a database.

Spring Boot needs the following information to create it:

- Database URL
- Username
- Password
- JDBC Driver

Without this information, the application cannot communicate with the database.

---

## Why did the application fail?

The application started successfully until Spring Boot attempted to create the DataSource.

Since no database configuration was provided, Spring Boot could not determine:

- Which database to connect to.
- Where the database is located.
- Which credentials should be used.

As a result, the application failed during startup.

# Session 9 - Connecting Spring Boot to MySQL

## Spring DataSource

A DataSource represents the connection between a Spring Boot application and a database.

Spring Boot needs:

- Database URL
- Username
- Password

Without these, it cannot communicate with the database.

---

## JDBC URL

Example:

```text
jdbc:mysql://localhost:3306/expense_tracker
```

Breakdown:

- `jdbc` → Java Database Connectivity
- `mysql` → Database type
- `localhost` → Database server running on the local machine
- `3306` → Default MySQL port
- `expense_tracker` → Database name

---

## JPA Configuration

### ddl-auto

```yaml
ddl-auto: update
```

Automatically creates or updates database tables based on Java entities.

Useful during development.

---

### show-sql

```yaml
show-sql: true
```

Prints executed SQL statements in the console.

Helpful for debugging and learning.

---

### format_sql

Formats SQL output to make it easier to read.

# Session 10 - First Successful Spring Boot Application

## Milestone Achieved

The application started successfully.

Key log messages:

- Tomcat started on port 8080
- Hikari connection pool started
- EntityManagerFactory initialized
- ExpenseTrackerApplication started

This confirms that:

- Spring Boot is configured correctly.
- Maven dependencies are working.
- MySQL connection is successful.
- Hibernate is initialized.
- Embedded Tomcat is running.

---

## HikariCP

HikariCP is the default database connection pool used by Spring Boot.

Instead of opening a new database connection for every request, it maintains a pool of reusable connections for better performance.

---

## Hibernate

Hibernate is the JPA implementation used by Spring Boot.

Its responsibilities include:

- Mapping Java entities to database tables.
- Generating SQL queries.
- Managing database operations.

# Session 11 - Java Version in Maven

## `<java.version>`

The `pom.xml` file contains:

```xml
<properties>
    <java.version>23</java.version>
</properties>
```

This tells Maven which Java language version to compile the project with.

It must match (or be lower than) the installed JDK version.

Example:

Installed JDK:

```
Java 23
```

Correct:

```xml
<java.version>23</java.version>
```

Incorrect:

```xml
<java.version>25</java.version>
```

because Java 23 cannot compile Java 25 code.

---

## Build Lifecycle

When running:

```bash
./mvnw clean package
```

Two important phases occur:

- `clean` → Deletes the `target` folder.
- `package` → Compiles the code and creates the application JAR.

If compilation fails, the `target/classes` folder will not contain updated `.class` files.

# Session 12 - First Working REST API

## Milestone

Successfully started a Spring Boot application and exposed the first REST endpoint.

Example:

GET /expenses

Response:

The controller api is working!

---

## Request Flow

Browser
    ↓
Spring Boot (Tomcat)
    ↓
ExpenseController
    ↓
Java Method
    ↓
Response returned to Browser

---

## Lessons Learned

- Spring Boot automatically scans classes under the main package.
- `@RestController` exposes REST endpoints.
- `@RequestMapping` defines the base URL.
- `@GetMapping` handles HTTP GET requests.
- `@PostMapping` handles HTTP POST requests.

---

## Debugging Lessons

The root cause of the 404 was not the controller itself.

The actual sequence was:

Java Version Mismatch
→ Compilation Failed
→ Controller Class Not Generated
→ Spring Could Not Register Endpoint
→ HTTP 404

Always trace symptoms back to the root cause.

# Session 13 - Layered Architecture

A Spring Boot application is divided into layers.

## Controller

Responsibilities:

- Receives HTTP requests.
- Calls the service layer.
- Returns HTTP responses.

The controller should not contain business logic.

---

## Service

Responsibilities:

- Contains business logic.
- Performs validation.
- Applies business rules.
- Coordinates multiple repositories if required.

The service decides **what** should happen.

---

## Repository

Responsibilities:

- Communicates with the database.
- Performs CRUD operations.
- Does not contain business logic.

The repository knows **how** to perform database operations.

---

## Typical Request Flow

Browser

↓

Controller

↓

Service

↓

Repository

↓

Database

Each layer has a single responsibility, making the application easier to maintain, test, and extend.

# Session 14 - Essential Git Commands

## 1. Check Repository Status

```bash
git status
```

Shows the current state of the repository.

Use it frequently.

---

## 2. View Changes

```bash
git diff
```

Displays the exact changes made since the last commit.

---

## 3. Stage Files

```bash
git add .
```

Stages all modified files for the next commit.

---

## 4. Create a Commit

```bash
git commit -m "Meaningful commit message"
```

Creates a snapshot of the staged changes.

Commits are local.

---

## 5. Push to GitHub

```bash
git push
```

Uploads local commits to the remote repository.

---

## 6. Pull from GitHub

```bash
git pull
```

Downloads the latest changes from the remote repository.

---

## 7. View Commit History

```bash
git log --oneline
```

Shows a compact list of commits.

---

## 8. View Remote Repositories

```bash
git remote -v
```

Displays the configured remote repositories.

---

## Recommended Workflow

```text
Code
↓

git status

↓

git diff

↓

git add .

↓

git commit -m "Meaningful message"

↓

git push
```

# Session 15 - Why the Service Layer Exists

The Service layer contains the application's business logic.

Controllers should not contain business logic.

## Problems if all logic is inside the Controller

1. The controller has too many responsibilities.
2. Debugging becomes more difficult.
3. The project becomes harder to understand for other developers.
4. Business logic cannot be easily reused.
5. The controller becomes very large and difficult to maintain.

---

## Responsibilities

### Controller

- Receives HTTP requests.
- Calls the service.
- Returns HTTP responses.

### Service

- Contains business logic.
- Performs validation.
- Applies business rules.
- Coordinates repositories.
- Reuses logic across multiple controllers.

# Session 16 - Dependency Injection (Introduction)

## Without Spring

Objects are created manually.

```java
ExpenseService expenseService = new ExpenseService();
```

The programmer is responsible for creating and managing objects.

---

## With Spring

Objects are created automatically by Spring.

Spring manages the lifecycle of these objects (called Beans).

Classes annotated with stereotypes such as:

- `@RestController`
- `@Service`
- `@Repository`

are detected during startup, and Spring creates their objects automatically.

This mechanism is called **Dependency Injection (DI)**.

Instead of creating dependencies manually, Spring injects them where required.

# Session 17 - Constructor Injection

Declaring a variable does **not** create an object.

Example:

```java
private ExpenseService expenseService;
```

This only declares a reference.

Spring must provide the object.

The recommended approach is **Constructor Injection**.

```java
@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
}
```

During application startup:

1. Spring creates an `ExpenseService` object.
2. Spring creates an `ExpenseController`.
3. Spring passes the `ExpenseService` object to the controller's constructor.
4. The controller can now use `expenseService`.

Constructor Injection is the recommended way to inject dependencies in modern Spring Boot applications.

# Session 17 - Dependency Injection (Deep Dive)

## Dependency

A dependency is another object that a class needs to do its work.

Example:

- ExpenseController depends on ExpenseService.
- ExpenseService depends on ExpenseRepository.

---

## Without Dependency Injection

```java
ExpenseService expenseService = new ExpenseService();
```

The class creates its own dependency.

This creates tight coupling.

---

## With Dependency Injection

```java
private final ExpenseService expenseService;

public ExpenseController(ExpenseService expenseService) {
    this.expenseService = expenseService;
}
```

The controller does not create the service.

Spring provides it through the constructor.

---

## Why Constructor Injection?

- Recommended by Spring.
- Makes dependencies explicit.
- Works well with `final` fields.
- Improves testability.
- Promotes loose coupling.

---

## Request Flow

Browser
↓
Controller
↓
Service
↓
Repository
↓
Database

Spring automatically connects the layers by injecting the required objects.

# Session 18 - Spring Beans and Singleton Scope

## Bean

A Bean is an object that is created and managed by Spring.

Examples:

- `ExpenseController`
- `ExpenseService`
- `ExpenseRepository`

---

## Spring Container

The Spring Container stores all the Beans created during application startup.

When one Bean depends on another, Spring provides the existing Bean instead of creating a new one.

---

## Singleton Scope (Default)

By default, Spring creates **one instance** of each Bean.

Example:

```
ExpenseService Bean
        ▲
        │
 ┌──────┴──────┐
 │             │
 ▼             ▼
ExpenseController   ReportController
```

Both controllers share the same `ExpenseService` object.

---

## Why Singleton?

- Saves memory.
- Faster startup and execution.
- Centralized object management.

---

## Best Practice

Service classes should generally be **stateless**.

Do not store request-specific data in instance variables.

Keep business logic inside methods.

# Session 19 - The `final` Keyword

## What does `final` mean?

A `final` variable can only be assigned once.

Example:

```java
final String name = "Nehul";
```

This is allowed:

```java
System.out.println(name);
```

This is **not** allowed:

```java
name = "Rahul";
```

---

## `final` with Objects

```java
final ExpenseService expenseService = new ExpenseService();
```

The reference cannot point to another object.

This is **not** allowed:

```java
expenseService = new ExpenseService();
```

However, calling methods is allowed:

```java
expenseService.addExpense();
```

---

## Why use `final` in Spring Boot?

- Ensures dependencies are initialized once.
- Prevents accidental reassignment.
- Makes code safer and easier to understand.
- Works naturally with Constructor Injection.

Most Spring Boot projects use:

```java
private final ExpenseService expenseService;
```