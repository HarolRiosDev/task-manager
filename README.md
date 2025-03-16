# Task Manager API

## 📌 Descripción
Task Manager es una API RESTful desarrollada con **Spring Boot** que permite gestionar tareas de manera eficiente. Implementa autenticación con JWT, paginación, filtrado y documentación con OpenAPI.

## 🚀 Tecnologías Utilizadas
- **Java 17+**
- **Spring Boot 3+**
- **Spring Web** (para API REST)
- **Spring Data JPA** (para persistencia)
- **Spring Security + JWT** (para autenticación)
- **H2 Database** (base de datos en memoria para desarrollo)
- **Lombok** (para reducir boilerplate)
- **Swagger / OpenAPI** (documentación de API)

## 📂 Estructura del Proyecto
```
/task-manager-spring
│── src/main/java/com/example/taskmanager
│   ├── controller  # Controladores REST
│   ├── service     # Lógica de negocio
│   ├── repository  # Capa de acceso a datos
│   ├── model       # Entidades y DTOs
│   ├── security    # Configuración de seguridad y JWT
│── src/main/resources
│   ├── application.properties  # Configuración de la BD y JWT
│   ├── data.sql	# Datos insertados en bbdd
│   ├── schema.sql	# Creación de tablas
│   ├── api-first
│	├──── task-manager.yml # fichero yml
│── README.md  # Este archivo
```

## 📖 Endpoints
### 🔐 Autenticación (JWT)
| Método | Endpoint               | Descripción |
|---------|------------------------|-------------|
| `POST`  | `/api/auth/register`   | Registro de usuario |
| `POST`  | `/api/auth/login`      | Iniciar sesión, devuelve un token JWT |
| `GET`   | `/api/auth/me`         | Datos del usuario autenticado |

### ✅ Tareas
| Método | Endpoint                 | Descripción |
|---------|-------------------------|-------------|
| `GET`   | `/api/tasks`             | Obtener todas las tareas (paginado y filtrado) |
| `GET`   | `/api/tasks/{id}`        | Obtener una tarea por ID |
| `POST`  | `/api/tasks`             | Crear una nueva tarea |
| `PUT`   | `/api/tasks/{id}`        | Actualizar una tarea |
| `PATCH` | `/api/tasks/{id}/status` | Cambiar estado de una tarea |
| `DELETE`| `/api/tasks/{id}`        | Eliminar una tarea |

### 👤 Usuarios
| Método | Endpoint          | Descripción |
|---------|-----------------|-------------|
| `GET`   | `/api/users`    | Obtener todos los usuarios |
| `GET`   | `/api/users/{id}` | Obtener un usuario por ID |

## ⚙️ Instalación y Ejecución
### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/HarolRiosDev/task-manager-spring.git
cd task-manager-spring
```

### 2️⃣ Configurar la Base de Datos (H2 en memoria)
No es necesario configurar nada adicional. H2 está listo para usarse con la siguiente configuración en `application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:taskdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
Para acceder a la consola de H2:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:taskdb`
- Usuario: `sa`
- Contraseña: *(vacío)*

### 3️⃣ Compilar y ejecutar
```bash
mvn clean install
mvn spring-boot:run
```

### 4️⃣ Acceder a la API
- API Base: `http://localhost:8080/api`
- Documentación Swagger: `http://localhost:8080/swagger-ui.html`

## 🛠 Próximos Pasos
✅ Mejorar la seguridad con OAuth2
✅ Integrar WebSockets para actualizaciones en tiempo real
✅ Desplegar en Docker y Kubernetes

---
**Autor:** [HarolRiosDev](https://github.com/HarolRiosDev)  
📅 Proyecto iniciado en Marzo 2025

