# Tienda API

API REST con Spring Boot para gestión de productos y categorías.

# Tecnologías

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL
- Docker + Docker Compose
- Maven
- Lombok

# Funcionalidades

- CRUD completo de categorías y productos
- Relación entre entidades
- Validacione s con mensajes de error
- Manejo de excepciones con respuestas HTTP
- Filtrado de productos por disponibilidad y por categoría
- Tests unitarios con JUnit 5 y Mockito

# Endpoints principales

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /categorias | Obtener todas las categorías |
| POST | /categorias | Crear una categoría |
| GET | /productos | Obtener todos los productos |
| GET | /productos/disponibles | Productos disponibles |
| GET | /productos/categoria/{id} | Productos por categoría |
| POST | /productos | Crear un producto |

# Cómo ejecutar

```bash
docker compose up
```

La API estará disponible en `http://localhost:8080`