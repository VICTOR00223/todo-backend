# 📝 Todo Backend API

A RESTful Spring Boot application for managing tasks, built with a focus on data integrity and clean architecture.

## 📋 Prerequisites
Before running this application, ensure you have the following installed on your "workbench":
* **Java 17 JDK** (Required to compile and run the code)
* **PostgreSQL** (Running locally on port 5432).
  * *Note: If you don't have it installed, you can download it [here](https://www.postgresql.org/download/).*
* **IDE** (IntelliJ IDEA, Eclipse, or VS Code)
  
## 🚀 Technical Highlights
* **Java 17 & Spring Boot 3.5.9**
* **PostgreSQL** for persistent storage.
* **JPA/Hibernate** for ORM with automated schema updates.
* **Validation**: Input checking using `@Valid` and `@NotBlank`.
* **Lombok**: Reduced boilerplate for cleaner entity classes.

## 🛠 Setup & Installation

### 1. Clone the Repository
First, bring the source code to your local machine:
```bash
git clone https://github.com/VICTOR00223/todo-backend.git
cd todo-backend
```
### 2. Database Configuration
Open your PostgreSQL terminal (psql) or a GUI tool like pgAdmin and run the following commands:
```sql
-- Create the database
CREATE DATABASE tododb;

-- Create a dedicated user (Matches default application.properties)
CREATE USER todouser WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE tododb TO todouser;
```
### 3. Application Properties
You must ensure the application is pointing to the database you created in the previous step. Navigate to src/main/resources/application.properties and verify/update the following:
```properties
# Database Connection
spring.datasource.url=jdbc:postgresql://localhost:5432/tododb
spring.datasource.username=todouser
spring.datasource.password=password

# Hibernate Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 4. Run the Application
Navigate to the project root directory in your terminal and execute the following command to start the Spring Boot server:

On Mac or Linux:
```bash
./mvnw spring-boot:run
```
On Windows:
```bash
mvnw.cmd spring-boot:run
```
### 5. Testing the API
Since this is a headless Backend API, you can verify it is working by sending requests via curl in your terminal.

A. Create a New Task (POST)
Run this command to add a todo to your PostgreSQL database:
```bash
curl -X POST http://localhost:8080/todos \
     -H "Content-Type: application/json" \
     -d '{"title": "My first task"}'
```
B. Retrieve All Tasks (GET)
To see all the tasks currently stored in your database (including the automatically generated IDs and timestamps), run:
```bash
curl http://localhost:8080/todos
```
C. Update a Task (PUT)
To change the status of a task (for example, marking the task with ID 1 as completed), use the following command. Note that we append the ID to the URL:
```bash
curl -X PUT http://localhost:8080/todos/1 \
     -H "Content-Type: application/json" \
     -d '{"completed": true}'
```
D. Remove a Task (DELETE)
```bash
curl -X DELETE http://localhost:8080/todos/1
```

## 📡 API Endpoints
* `GET /todos` - Fetch all tasks.
* `POST /todos` - Create a new task (Title is required).
* `PUT /todos/{id}` - Update task details or completion status.
* `DELETE /todos/{id}` - Remove a task.
