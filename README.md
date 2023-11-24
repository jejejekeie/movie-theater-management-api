Overview
This Spring Boot project provides a comprehensive API for managing a movie database. It encompasses entities such as movies (Película), cinemas (Cine), and reviews (Review), offering a rich set of functionalities including CRUD operations for these entities, sorting and filtering capabilities, and a security layer for API access.

Features
Entity Management: Handle movies, cinemas, and reviews with dedicated endpoints.
Data Relationships: Many-to-many relations between movies and cinemas; one-to-many between movies and reviews.
Security: Basic authentication with role-based access.
Filtering and Sorting: Retrieve cinemas by postal code or price, and reviews by their rating.
Error Handling: Custom error responses for invalid operations or data not found scenarios.

Technology Stack
Spring Boot: Framework for creating stand-alone, production-grade Spring-based applications.
Spring Data JPA: For database operations.
Spring Security: For securing the API.
MySQL: As the database for storing entities.
Hibernate: ORM tool for data mapping.
Jakarta Persistence API: For database operations.
JUnit & Spring Boot Test: For testing purposes.

Getting Started
Prerequisites
JDK 1.8 or later
Maven 3.2+
MySQL Server

Running the Application
Clone the repository: git clone [repository URL]
Navigate to the project directory: cd [project folder]
Configure MySQL:
Ensure MySQL service is running.
Update application.properties with your MySQL username and password.
Run the application: mvn spring-boot:run

API Endpoints
Movies (Pelicula):
GET /api/peliculas
POST /api/peliculas
PUT /api/peliculas/{id}
DELETE /api/peliculas/{id}
Cinemas (Cine):
GET /api/cines
POST /api/cines
PUT /api/cines/{id}
DELETE /api/cines/{id}
Reviews (Review):
GET /api/reviews
POST /api/reviews
PUT /api/reviews/{id}
DELETE /api/reviews/{id}

Security
The API uses basic authentication with two roles: ADMIN and USER. Modify SecurityConfig class for different security configurations.


License
Distributed under the MIT License. See LICENSE for more information.
