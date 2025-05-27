# Proyecto Pastelería Artesanal 🍰

Sistema de gestión de pedidos, productos y control de stock para una pastelería artesanal.
Permite registrar clientes, gestionar pedidos personalizados, mantener trazabilidad de los estados del pedido y generar reportes gerenciales.

---

## 📄 Descripción General

Proyecto realizado con **Spring Boot 3**, **H2 Database**, arquitectura **MVC**, manejo de errores global, validaciones y documentación Swagger/OpenAPI.

Incluye:

* CRUD de clientes, empleados y productos
* Creación y gestión de pedidos con personalización
* Control de estados y roles de empleados
* Reportes gerenciales

---
## Reglas de Negocio
## Clientes

**RN1:** Un cliente debe registrarse con nombre completo, número de teléfono y correo electrónico. (Opcional: dirección de entrega).

**RN2:** Un cliente puede tener múltiples pedidos en el sistema, pero no puede realizar más de **3 pedidos activos** (es decir, pedidos en proceso de producción o pendientes de entrega).

---

## Productos (Pasteles y productos artesanales)

**RN3:** Cada producto debe tener: nombre, descripción, precio base, disponibilidad (activo/inactivo) y stock mínimo requerido de ingredientes.

**RN4:** Los productos pueden tener personalizaciones opcionales (por ejemplo, sabor, relleno, decoración especial) que aumentan el precio base.

**RN5:** No se pueden ofrecer productos cuya disponibilidad esté marcada como inactiva o que no tengan stock suficiente.

---

## Pedidos

**RN6:** Un pedido debe contener al menos un producto.

**RN7:** Cada pedido debe tener: fecha de creación, fecha de entrega estimada, estado del pedido, lista de productos solicitados, total a pagar.

**RN8:** Los pedidos pueden ser cancelados hasta **48 horas antes de la fecha de entrega programada**. Después de eso, no pueden cancelarse.

**RN9:** El estado del pedido puede ser:

- "Solicitado"
- "Confirmado"
- "En Producción"
- "Listo para Entregar"
- "Entregado"
- "Cancelado"

**RN10:** No se puede cambiar el estado de un pedido a "Listo para Entregar" si no ha pasado primero por "En Producción".

---

## Empleados

**RN11:** Un empleado puede cambiar el estado de un pedido según su rol:

- Solo los de **Producción** pueden marcar un pedido como "En Producción" o "Listo para Entregar".
- Solo los de **Despacho** pueden marcar como "Entregado".

**RN12:** Cada cambio en el historial de estados debe registrar **quién lo hizo**.

---
## 📊 Reportes

- **Clientes y sus pedidos:** Mostrar cada cliente junto con el número de pedidos que ha realizado. (Básico)
- **Productos más solicitados:** Listar los productos más pedidos en el último mes. (Básico)
- **Pedidos pendientes de entrega:** Mostrar pedidos con estado "Confirmado" o "En Producción", ordenados por fecha de entrega. (Básico)
- **Pedidos cancelados:** Resumen de pedidos cancelados en un periodo. (Básico)
- **Pedidos entregados:** Listar todos los pedidos entregados en el último mes y calcular el total de ingresos. (Esencial)

---

## 📊 Diagrama de Entidades

> Puedes ver el diagrama en src/resources/PasteleriaDiagramaEntidadRelacion.png

Relaciones principales:

* Cliente 1\:N Pedido
* Pedido 1\:N PedidoProducto
* Producto 1\:N PedidoProducto
* Pedido 1\:N HistorialEstado
* Estado 1\:N Pedido / HistorialEstado
* Empleado 1\:N HistorialEstado

---

## 📆 Instrucciones de Instalación

1. Clonar el repositorio
2. Importar como proyecto Gradle en IntelliJ o Eclipse
3. Ejecutar la clase `PasteleriaApplication.java`
4. Acceder a:

   * Swagger UI: `http://localhost:8080/swagger-ui.html`
   * Consola H2: `http://localhost:8080/h2-console`
   * JDBC URL: `jdbc:h2:mem:pasteleriaDB` (usuario: `sa`, sin contraseña)

---

## 🔍 Documentación de Endpoints

### Clientes

| Método | Ruta                                 | Descripción                                    |
| ------ | ------------------------------------ | ---------------------------------------------- |
| POST   | `/api/clientes`                      | Crear cliente (valida nombre, teléfono, email) |
| GET    | `/api/clientes`                      | Listar todos los clientes                      |
| GET    | `/api/clientes/{id}/pedidos-activos` | Ver cuántos pedidos activos tiene un cliente   |

### Productos

| Método | Ruta                                 | Descripción                  |
| ------ | ------------------------------------ | ---------------------------- |
| POST   | `/api/productos`                     | Crear producto artesanal     |
| GET    | `/api/productos`                     | Listar productos disponibles |
| PUT    | `/api/productos/{id}/disponibilidad` | Cambiar disponibilidad       |

### Pedidos

| Método | Ruta                             | Descripción                           |
| ------ | -------------------------------- | ------------------------------------- |
| POST   | `/api/pedidos`                   | Crear pedido personalizado            |
| GET    | `/api/pedidos`                   | Ver todos los pedidos                 |
| PUT    | `/api/pedidos/{id}/estado`       | Cambiar estado (según rol y reglas)   |
| DELETE | `/api/pedidos/{id}?empleadoId=X` | Cancelar pedido (solo si faltan >48h) |

### Empleados

| Método | Ruta             | Descripción                   |
| ------ | ---------------- | ----------------------------- |
| POST   | `/api/empleados` | Crear empleado (rol validado) |
| GET    | `/api/empleados` | Listar empleados              |

### Reportes

| Método | Ruta                                    | Descripción                                    |
| ------ | --------------------------------------- | ---------------------------------------------- |
| GET    | `/api/reportes/clientes-pedidos`        | Clientes y cantidad de pedidos                 |
| GET    | `/api/reportes/productos-mas-vendidos`  | Top productos vendidos último mes              |
| GET    | `/api/reportes/pedidos-pendientes`      | Pedidos con estado "Confirmado" o "Producción" |
| GET    | `/api/reportes/pedidos-cancelados`      | Pedidos cancelados en un periodo               |
| GET    | `/api/reportes/pedidos-entregados`      | Entregados último mes + total de ingresos      |
| GET    | `/api/reportes/ingredientes-bajo-stock` | Ingredientes por debajo del stock mínimo       |

---

## 💪 Ejemplos de Peticiones

### Crear Cliente

```http
POST /api/clientes
Content-Type: application/json

{
  "nombreCompleto": "Lucía García",
  "telefono": "3001234567",
  "email": "lucia@email.com",
  "direccion": "Calle 123 #4-56"
}
```

### Crear Pedido

```http
POST /api/pedidos
Content-Type: application/json

{
  "clienteId": 1,
  "fechaEntregaEstimada": "2025-05-18T10:00:00",
  "productos": [
    {
      "productoId": 2,
      "cantidad": 2,
      "personalizacion": "Relleno de Nutella"
    }
  ]
}
```

---

## 🔍 Swagger UI

Documentación interactiva:

```
http://localhost:8080/swagger-ui.html
```

---

## 📊 Colección Postman

Puedes descargar el archivo `Pasteleria.postman_collection.json` que incluye pruebas para todos los endpoints.
> Puedes verlo en src/resources/Pasteleria.postman_collection.json

---

## 🚀 Stack Tecnológico

* Java 17
* Spring Boot 3.2+
* Spring Data JPA
* H2 In-Memory Database
* Swagger / OpenAPI (springdoc)
* Bean Validation (jakarta)
* Lombok

---

> Proyecto académico inspirado en la gestión de pedidos de una pastelería real. Diseñado con enfoque en buenas prácticas, arquitectura limpia y reglas de negocio claras.
