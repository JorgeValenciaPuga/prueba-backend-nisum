
# Proyecto de Backend para Creación de Usuarios

## Descripción
Este es un proyecto para la creación de usuarios y su gestión de teléfonos, utilizando **Spring Boot** con **Java 17** y **JWT** para la autenticación. El proyecto también tiene habilitado **OpenAPI** para documentar los endpoints.

## Configuración
1. Asegúrate de tener **Java 17** y **Maven** instalados en tu máquina.
2. Clona el repositorio del proyecto.
3. Ejecuta `mvn clean install` para compilar y generar el proyecto.
4. Ejecuta el proyecto con el comando `mvn spring-boot:run`.

## Endpoints

### 1. **Registrar Usuario**
- **URL**: `/api/usuarios/registrar`
- **Método**: `POST`
- **Descripción**: Este endpoint permite registrar un nuevo usuario con sus datos básicos y teléfonos asociados.
- **Cuerpo de la solicitud (JSON)**:
    ```json
    {
      "name": "nombreusuario",
      "email": "email@dominio.com",
      "password": "contrasena123",
      "phones": [
        {
          "number": "1234567890",
          "citycode": "1",
          "countrycode": "57"
        }
      ]
    }
    ```

#### **Respuesta exitosa**:
```json
{
  "id": "UIID",
  "name": "nombreusuario",
  "email": "emailregistrado",
  "created": "2025-04-07T16:57:58.7530123",
  "modified": "2025-04-07T16:57:59.0350611",
  "last_login": "2025-04-07T16:57:59.0350611",
  "token": "token",
  "isactive": true
}
```

#### **Respuesta de error**:
```json
{
  "status": "error",
  "message": "Detalles del error"
}
```


## Instrucciones para probar los endpoints

### 1. **Probar con Postman o cURL**
Puedes utilizar herramientas como **Postman** o **cURL** para probar los endpoints de la API. A continuación se muestra un ejemplo de cómo hacer la prueba de registro de un usuario con cURL:

```bash
curl -X POST http://localhost:8080/api/usuarios/registrar -H "Content-Type: application/json" -d '{
  "name": "Juan Perez",
  "email": "juan.perez@example.com",
  "password": "12345",
  "phones": [
    {
      "number": "987654321",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}'
```

### 2. **Swagger UI**
El proyecto tiene habilitada la interfaz de Swagger UI para explorar y probar los endpoints de manera visual. Puedes acceder a ella a través de la siguiente URL:

```
http://localhost:8080/swagger-ui.html
```

### 3. **Ver el OpenAPI**
Para ver la documentación del API en formato OpenAPI, puedes acceder al siguiente endpoint:

```
http://localhost:8080/v3/api-docs
```

---

## Configuración adicional

### Base de Datos H2
Este proyecto usa **H2** como base de datos en memoria. Puedes acceder a la consola de H2 en:

```
http://localhost:8080/h2-console
```

Configura los siguientes parámetros para acceder:

- **JDBC URL**: `jdbc:h2:mem:taskdbnisum`
- **Username**: `sa`
- **Password**: `password`

---

### Configuración de JWT
La autenticación de la API se maneja mediante JWT. Para obtener el token, debes pasar las credenciales correctas en el endpoint de registro o login, y luego usar el token para autenticar otras solicitudes.

---

### Información adicional

**Autor:** Jorge Valencia

