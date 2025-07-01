📚 Library Management System
This is a Spring Boot based backend application for managing a simple library system. It supports basic CRUD operations for books, authors, publishers, and categories, as well as book borrowing with stock control. PostgreSQL is used as the database.

🛠️ Technologies Used

Java 17

Spring Boot 3

Spring Data JPA

PostgreSQL

Lombok

ModelMapper

Jakarta Bean Validation

Swagger UI (springdoc-openapi)

📦 Features

Book Management: Add, update, delete, and list books

Author / Publisher / Category Management

Book Borrowing:

Validates stock availability

Decreases stock upon borrowing

Prevents category deletion if books are associated with it

DTO ↔ Entity mapping via ModelMapper

Validation on input fields

Swagger UI for testing and exploring API endpoints

🔄 Sample API Endpoints

Endpoint	Description

POST /v1/books	Add a new book

GET /v1/books	Get all books

POST /v1/book-borrowings	Borrow a book

DELETE /v1/categories/{id}	Delete category (with check)

🧪 Sample Book Borrowing Request

{
  "borrowerName": "Ahmet Yılmaz",
  "borrowingDate": "2025-06-20",
  "returnDate": "2025-07-05",
  "bookId": 1
}

▶️ Running the Project

Create a PostgreSQL database named library_with_SB

Update your database credentials in application.properties

Run the application via Maven or your IDE (e.g., IntelliJ)

Open Swagger UI for API documentation:

http://localhost:8081/swagger-ui.html
