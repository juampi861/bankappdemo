
#BankApp Demo

Este proyecto es un microservicio REST construido con Java y Spring Boot que implementa un CRUD completo de entidades bancarias, siguiendo una arquitectura hexagonal.

---

## 🔧 Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web (REST)**
- **Spring Data JPA**
- **Spring Security**
- **Spring Cache**
- **Springdoc OpenAPI (Swagger)**
- **H2 In-Memory Database**
- **Arquitectura Hexagonal**

---
![image](https://github.com/user-attachments/assets/a2c8d2d8-b3b7-436c-8e18-4886d1ef7fd1)


---

## 🧩 Decisiones de diseño

### ✅ Arquitectura Hexagonal
- Separación clara entre **dominio**, **aplicación** y **adaptadores**.
- Uso de interfaces (Ports) para acceder a infraestructura.

### ✅ Servicio único
- Se optó por un único servicio de aplicación (`BankService`) en lugar de múltiples interactors para mantener simplicidad en este ejemplo.

### ✅ Cache con Spring
- Se utilizó **Spring Cache** para mejorar el rendimiento del método `getAllBanks()`.
- Se invalida el caché automáticamente en operaciones de creación, actualización y eliminación.

### ✅ Documentación con Swagger
- Los endpoints están documentados con **Springdoc OpenAPI**.
- Accesible desde: `http://localhost:8080/swagger-ui.html`

### ✅ Seguridad con Spring Security
- Se implementó autenticación básica (Basic Auth) con usuario en memoria.
- Swagger y H2 están configurados para ser accesibles sin autenticación.

---

## 🧪 Endpoints disponibles

| Método | Endpoint               | Descripción                        |
|--------|------------------------|------------------------------------|
| GET    | `/banks`               | Listar todos los bancos            |
| GET    | `/banks/{id}`          | Obtener banco por ID               |
| POST   | `/banks`               | Crear un nuevo banco               |
| PUT    | `/banks/{id}`          | Actualizar un banco existente      |
| DELETE | `/banks/{id}`          | Eliminar un banco                  |
| GET    | `/banks/internal/{id}` | Endpoint que se autoinvoca via REST |

---

## Autenticación

Autenticación básica activada para todos los endpoints excepto Swagger y H2.

**Credenciales por defecto:**

- Usuario: `admin`
- Contraseña: `12345`

---

## 🚀 Ejecutar el proyecto

1. Clonar el repositorio
2. Ejecutar `./mvnw spring-boot:run` o desde tu IDE
3. Acceder a:
   - Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## Posibles mejoras

- Se agrego test unitario al DefaultBankService. Se podrian agregar otros tests unitarios y de integración.
- Validaciones más específicas.
- Externalizar configuración para múltiples entornos.

