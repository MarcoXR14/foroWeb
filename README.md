# ForoWeb

ForoWeb es el backend de un foro de internet desarrollado en Java con Spring Boot. Este proyecto expone una API RESTful para la gestión de tópicos y autenticación de usuarios.

## Tecnologías y Dependencias

El proyecto utiliza **Java 21**, **Spring Boot 4.0.3** y gestiona las dependencias a través de **Maven**. A continuación, se detallan las principales dependencias instaladas en `pom.xml`:

- `spring-boot-starter-webmvc`: Para la construcción de la API REST.
- `spring-boot-starter-data-jpa`: Para la persistencia de datos usando JPA e Hibernate.
- `spring-boot-starter-security`: Para la autenticación y autorización.
- `spring-boot-starter-validation`: Para la validación de los datos entrantes (Bean Validation).
- `spring-boot-starter-flyway` y `flyway-database-postgresql`: Para la gestión y ejecución de migraciones en la base de datos PostgreSQL.
- `java-jwt` (com.auth0, version 4.5.0): Para la generación y validación de JSON Web Tokens (JWT).
- `postgresql`: Driver para la conexión a la base de datos PostgreSQL.
- `lombok`: Para reducir el código boilerplate (getters, setters, constructores, etc.).
- `spring-boot-devtools`: Herramientas de desarrollo, como la recarga automática.

Adicionalmente, se incluyen diversas dependencias de testing (`spring-boot-starter-data-jpa-test`, `spring-boot-starter-webmvc-test`, `spring-security-test`, etc.).

## Estructura de Clases Java

El proyecto cuenta con las siguientes clases e interfaces distribuidas según sus responsabilidades:

### Configuración principal
- `ForoWebApplication.java`: Clase principal para iniciar la aplicación Spring Boot.

### Controladores (Controllers)
Gestionan las peticiones HTTP entrantes.
- `LoginControler.java`: Endpoints para la autenticación de usuarios.
- `TopicosController.java`: Endpoints para gestionar los tópicos del foro.

### Dominio (Domain)
Contiene las entidades, DTOs (Data Transfer Objects) y repositorios.
**Tópicos:**
- `Topico.java`: Entidad principal que representa un tópico en la base de datos.
- `Estado.java`: Enumeración para los estados posibles de un tópico.
- `TopicoRepositorio.java`: Interfaz repositorio JPA para interactuar con la base de datos de tópicos.
- *DTOs*: `DatosActualizarTopicos.java`, `DatosDetalleTopico.java`, `DatosListaTopico.java`, `DatosRegistroTopico.java`.

**Usuarios:**
- `Usuarios.java`: Entidad que representa a un usuario registrado.
- `UsuarioRepositorio.java`: Interfaz repositorio JPA para los usuarios.
- `AutenticacionService.java`: Servicio para implementar la lógica de inicio de sesión de Spring Security.
- *DTOs*: `DatosAutenticacion.java`.

### Infraestructura (Infra)
Componentes transversales y configuración de seguridad.
- `SecurityConfiguration.java`: Configuración principal de Spring Security.
- `SecurityFilter.java`: Filtro para interceptar peticiones, extraer y validar el token JWT.
- `TokenService.java`: Servicio encargado de generar y verificar tokens JWT.
- `DatosTokenJWT.java`: Record DTO para enviar el JWT generado de vuelta al cliente.

### Testing
- `ForoWebApplicationTests.java`: Clase para los tests de contexto de la aplicación.

## Configuración (`application.properties`)

La configuración de la aplicación y conexión a la base de datos se maneja a través del archivo `application.properties`:

```properties
spring.application.name=ForoWeb

# Conexión a la Base de Datos PostgreSQL
spring.datasource.url=jdbc:postgresql://${DB_HOST}/foroWeb
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDirect

# Configuración de JPA e Hibernate
# Permite actualizar la estructura de las tablas directamente desde las entidades
spring.jpa.hibernate.ddl-auto=update
# Muestra y formatea el SQL generado por Hibernate en la consola
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format-sql=true

# Ajuste de errores web (no mostrar el stacktrace al cliente)
spring.web.error.include-stacktrace=never

# Secreto para la firma y verificación de los tokens JWT
api.security.token.secret=${JWT_SECRET:12345678}
```

*Nota: Se recomienda usar variables de entorno (`DB_HOST`, `DB_USER`, `DB_PASSWORD`, `JWT_SECRET`) para que las credenciales no estén hardcodeadas en un entorno de producción.*
