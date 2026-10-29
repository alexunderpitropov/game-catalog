# 🎮 Game Catalog

---

## 🔷 What Is This, Exactly

**Game Catalog** is a web application for managing a video game catalog, developed as a coursework project at USM.

The application allows users to manage games, developers, and genres through a web interface. It includes CRUD operations, sorting, cover image uploads, and a REST API for working with game data.

**Stack:** Java 17 + Spring Boot 3.5 + Thymeleaf + Bootstrap 5 + PostgreSQL.

---

## 🔷 Stack - Briefly, What Is Used for What

| Technology | Purpose |
|---|---|
| Java 17 | Main programming language |
| Spring Boot 3.5 | Application configuration and backend development |
| Spring MVC | Handles HTTP requests and routes |
| Spring Data JPA | Database access through repositories |
| Hibernate | ORM for mapping Java objects to database tables |
| PostgreSQL | Relational database |
| Thymeleaf | Server-side HTML rendering |
| Bootstrap 5 | Responsive interface and styling |
| Lombok | Reduces boilerplate code |
| Maven | Dependency management and project build |

---

## 🔷 Backend Architecture

The application follows the MVC pattern and uses a layered backend structure:

```text
Controller → Service → Repository → Entity → PostgreSQL
```

- **Controller** (`@Controller`, `@RestController`) - handles incoming HTTP requests and returns web pages or JSON responses.
- **Service** (`@Service`) - contains the application's business logic.
- **Repository** (`JpaRepository`) - handles database operations through Spring Data JPA.
- **Entity** (`@Entity`) - represents database tables as Java classes.
- **DTO** - used for transferring data between application layers.

The application combines server-side rendering with Thymeleaf and a REST API that returns JSON.

---

## 🔷 Database - 4 Tables

| Table | What it stores |
|---|---|
| `games` | Game information, including name, price, release date, cover, developer, and genres |
| `developers` | Video game developers |
| `genres` | Available game genres |
| `game_genre` | Many-to-Many relationship between games and genres |

### Relationships

- **Games ↔ Genres:** Many-to-Many. A game can have multiple genres, and each genre can belong to multiple games.
- **Games → Developers:** Many-to-One. Multiple games can be associated with the same developer.

---

## 🔷 Application Functionality

### Game Catalog

- View games with their covers and details.
- Display prices and release dates.
- View associated developers and genres.
- Sort games by name, price, and release date.
- Upload game cover images.

### Management

CRUD operations are available for all three main entities:

- Games
- Developers
- Genres

Users can create, view, update, and delete records through the web interface.

### REST API

The application also provides a REST API for working with game data.

- Returns data in JSON format.
- Allows application data to be accessed through HTTP requests.
- Complements the Thymeleaf-based web interface.

Refer to the controller classes in the source code for the exact API endpoints.

---

## 🔷 Frontend

The user interface is built with **Thymeleaf** and **Bootstrap 5**.

- Thymeleaf renders HTML pages on the server.
- Bootstrap provides responsive layouts and UI components.
- The interface uses a dark theme inspired by Steam.

Unlike a standalone SPA, the application uses server-side rendering through Spring MVC.

---

## 🔷 Running the Project

### Requirements

- Java 17
- PostgreSQL
- Maven Wrapper

### 1. Clone the Repository

```bash
git clone <repository-url>
cd game-catalog
```

### 2. Configure PostgreSQL

Create a database in PostgreSQL and configure the connection in:

`src/main/resources/application.properties`

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<database-name>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

Replace the placeholders with your local database name and credentials.

### 3. Start the Application

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

**Linux / macOS:**

```bash
./mvnw spring-boot:run
```

Once Spring Boot starts successfully, open the local address configured for the application.

---

## 🔷 Project Purpose

This project was developed as coursework at USM to practice Java web development, Spring Boot, MVC architecture, database relationships, CRUD operations, server-side rendering, and REST API development.
