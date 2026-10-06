
# Java MVC Front Controller - Article Management

A Java Web application demonstrating the Model-View-Controller (MVC) architecture from scratch using Jakarta EE (Servlets & JSP) and JDBC. This project was built as an academic workshop at ENSA Tanger to explore web routing, data persistence, and architectural separation of concerns without relying on heavy frameworks like Spring.

## 🚀 Features
*   **Custom Front Controller:** A single central Servlet (`FrontController.java`) intercepts and routes all HTTP requests using `request.getServletPath()`.
*   **Complete CRUD Operations:** Create, Read, Update, and Delete articles.
*   **MVC Architecture:** Strict separation between the database access logic (DAO), request routing (Controller), and user interface (JSP Views).
*   **Secure Views:** All JSP files are protected inside the `WEB-INF/views` directory, forcing clients to pass through the controller.
*   **Session Flash Messages:** Seamless success/error notifications that survive redirects without polluting the URL.
*   **JDBC Persistence:** Data is securely stored in a PostgreSQL database using a Singleton Connection Manager and PreparedStatements to prevent SQL injection.

## 🛠️ Tech Stack
*   **Backend:** Java, Jakarta EE (Servlets 5.0+), JDBC
*   **Frontend:** HTML, CSS, JSP (JavaServer Pages), JSTL
*   **Database:** PostgreSQL
*   **Server:** Tomcat / WildFly
*   **Containerization:** Podman / Docker (for database hosting)

## 📂 Project Architecture
```text
src/main/
├── java/com/example/atelie1/
│   ├── controller/
│   │   └── FrontController.java       # Central router
│   └── model/
│       ├── Article.java               # Business entity
│       ├── ConnectionDB.java          # Singleton DB Connection
│       └── DaoArticle.java            # Singleton Data Access Object
└── webapp/
    └── WEB-INF/
        └── views/
            ├── listeArticles.jsp      # READ & DELETE view
            ├── Article.jsp            # CREATE view
            └── EditArticle.jsp        # UPDATE view

```

## ⚙️ Setup & Installation

### 1. Start the PostgreSQL Database

You can quickly spin up a PostgreSQL instance using Podman (or Docker) by running the following command in your terminal:

```bash
podman run --name postgres-crud-app \
  -e POSTGRES_DB=atelier_crud \
  -e POSTGRES_USER=atelier_user \
  -e POSTGRES_PASSWORD=atelier_pass \
  -p 5433:5432 \
  -d postgres

```

### 2. Configure the Application

Ensure the database credentials in `ConnectionDB.java` match your container setup. The application is designed to automatically create the `articles` table and insert initial seed data (A001, A002, A003) on the first run if the database is empty.

### 3. Deploy

Package the project using Maven and deploy the generated `.war` file to your preferred Servlet container (e.g., Apache Tomcat or JBoss WildFly).

