# TaskFlow - Project Management & Task Tracking REST API

TaskFlow is a backend RESTful API built with Java, Spring Boot, and MySQL. The system models multi-user collaboration across projects, team membership roles, task management, and discussions.

# Tech Stack

* Backend: Java 17+, Spring Boot 3
* Persistence & ORM: Spring Data JPA, Hibernate
* Database: MySQL
* API Documentation: Springdoc OpenAPI, Swagger UI
* Build & Dependency Management: Maven
* Testing & Tooling: Postman, Git

# System Architecture & Entity Relationship Diagram

GitHub renders this diagram directly from the code below:

```mermaid
erDiagram
    USERS ||--o{ PROJECTS : "creates"
    USERS ||--o{ PROJECT_MEMBERS : "joins"
    USERS ||--o{ TASKS : "assigned_to"
    USERS ||--o{ COMMENTS : "writes"

    PROJECTS ||--o{ PROJECT_MEMBERS : "contains"
    PROJECTS ||--o{ TASKS : "has"

    TASKS ||--o{ COMMENTS : "receives"

    USERS {
        int user_id PK
        string username
        string email
    }

    PROJECTS {
        int project_id PK
        string project_name
        string description
        int created_by FK
    }

    PROJECT_MEMBERS {
        int project_member_id PK
        int project_id FK
        int user_id FK
        string role
    }

    TASKS {
        int task_id PK
        int project_id FK
        int assigned_to_user_id FK
        string title
        string description
        string status
        int priority
    }

    COMMENTS {
        int comment_id PK
        int task_id FK
        int user_id FK
        string content
    }
