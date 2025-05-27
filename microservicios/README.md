# 🛠️ Guía de Ejecución y Conexión entre Microservicios - Proyecto Spring Boot

## ✅ Pasos iniciales para ejecutar el proyecto

1. **Clonar o descargar el repositorio**
    
    Descarga el repositorio desde GitHub o descomprime el archivo `.zip` que contiene el proyecto.
    
2. **Instalar dependencias de cada microservicio**
    
    Desde la raíz del proyecto, abre una terminal y ubícate dentro de cada microservicio para ejecutar el siguiente comando:
    
    ```powershell
    mvn clean install -DskipTests
    ```
    
- Esto compila los servicios sin ejecutar pruebas, asegurando que estén listos para correr.
1. **Levantar contenedores de Docker**
    
    Ejecuta este comando desde la raíz del proyecto para levantar los servicios de infraestructura:
    
    ```
    docker compose up -d
    ```
    
- Esto iniciará contenedores como la base de datos `mysql` y el `keycloak`, si está configurado.
1. **Ejecutar los microservicios en el siguiente orden:**
    
    Desde una terminal (o varias), ejecuta cada microservicio con:
    
    ```powershell
    mvn spring-boot:run
    ```
    
    El orden correcto de ejecución es:
    
    1. `config-server`
    2. `eureka-server`
    3. `api-gateway`
    4. El resto de microservicios (`user-service`, `order-service`, `product-service`, etc.)

---

## 🔗 ¿Cómo se conectan los microservicios?

El proyecto está basado en la arquitectura de microservicios usando el ecosistema de **Spring Cloud**. A continuación te explico cómo se comunican entre sí:

### 1. **Config Server (config-server)**

- Provee la configuración centralizada para todos los microservicios.
- Usa un repositorio Git como fuente de los archivos `application.yml` para cada micro.
- Cada microservicio, al iniciar, consulta a `config-server` para cargar su configuración específica según el `spring.application.name`.

```powershell
spring.cloud.config.uri=http://localhost:8888
```

### 2. **Eureka Server (eureka-server)**

- Actúa como servidor de descubrimiento (Service Registry).
- Todos los microservicios se registran en Eureka, incluyendo el `api-gateway`.
- Esto permite que los micros se comuniquen entre sí por nombre lógico y no por IP fija.

```powershell
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

### 3. **API Gateway (api-gateway)**

- Funciona como punto de entrada único para todas las solicitudes HTTP externas.
- Redirige las solicitudes a los microservicios registrados en Eureka usando su nombre lógico.
- También puede aplicar filtros, autenticación, rate limiting, etc.

```yaml
routes:
  - id: product-service
    uri: lb://product-service
    predicates:
      - Path=/api/product/**
```

> lb:// significa "load-balanced" y se apoya en Eureka para resolver la dirección del servicio destino.
> 

### 4. **Comunicación entre microservicios**

- Se realiza mediante llamadas HTTP usando **RestTemplate** o **Feign Client**.
- Los servicios usan Eureka para descubrir otros micros y comunicarse de forma desacoplada.
- También pueden usar colas (RabbitMQ, Kafka) para comunicación asincrónica si estuviera configurado.

---

## 🧩 Diagrama simplificado de conexión

```
        [ Client / Frontend ]
                 |
           [ API Gateway ]
                 |
   ----------------------------------
   |         |             |        |
Product   Order       User    Otros micros
Service   Service     Service
```

Cada uno:

- Recupera su configuración del `Config Server`
- Se registra en `Eureka Server`
- Es accedido a través del `API Gateway` por medio de rutas definidas

---

## 📮 Prueba de endpoints en Postman

Una vez que todos los microservicios están corriendo correctamente y se ha confirmado que `Config Server`, `Eureka Server` y el `API Gateway` están funcionando, podemos proceder a validar que los servicios están **operativos** y respondiendo correctamente a través de **Postman**.

### POST 	/v1/auth/register

```json
    
{
  "username": "EdwinRivera",
  "password": "EdwinRivera123",
  "email": "EdwinRivera@gmail.com"
}
```


### POST 	/v1/auth/login

```json
    
{
	"username": "EdwinRivera",
	"password": "EdwinRivera123"
}
```


### GET 	/v1/user/getAll

Para este caso había que actualizar el rol del usuario en el container de docker

Se obtiene el token una vez tiene el rol de ADMIN y ya si nos deja consultar:


### GET 	/v1/user/getUserById/{id}


### GET 	/v1/user/getUserByEmail/{email}


### DELETE 	/v1/user/deleteUserById/{id}

### POST 	/v1/job-service/category/create
```json
    
{
	"username": "EdwinRivera",
	"password": "EdwinRivera123"
}
```