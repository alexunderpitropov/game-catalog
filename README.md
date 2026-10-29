# Game Catalog

A web application for managing a video game catalog, developed as a coursework project at USM.

## Features

- View games with covers, prices, release dates, developers, and genres.
- Create, edit, and delete games, developers, and genres.
- Sort games by name, price, and release date.
- Upload game cover images.
- Manage game genres and developers.
- REST API for working with game data in JSON format.
- Dark-themed interface inspired by Steam.

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Data JPA and Hibernate
- PostgreSQL
- Thymeleaf
- Bootstrap 5
- Lombok
- Maven

## Architecture

The application follows the MVC pattern with a layered backend consisting of Controller, Service, Repository, and Entity layers. DTOs are used for data transfer.

The database contains four tables: `games`, `developers`, `genres`, and `game_genre`. Games have a many-to-many relationship with genres and a many-to-one relationship with developers.

## Running the Project

Requires Java 17 and PostgreSQL.

Configure the database connection in `src/main/resources/application.properties`, then run the application using Maven Wrapper:

**Windows**
```bash
mvnw.cmd spring-boot:run
```

**Linux / macOS**
```bash
./mvnw spring-boot:run
```
