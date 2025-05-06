# Proyecto 1ra Entrega - Desarrollo de API REST con Spring Boot

## 📌 Objetivo
Desarrollar una aplicación Spring Boot que resuelva un problema de negocio real, aplicando buenas prácticas de diseño, validaciones, documentación y manejo de datos. El proyecto debe ser entregado con código funcional, documentación técnica y evidencias de pruebas.

---

## 🛠 Requisitos Técnicos

### 1. Definición del Negocio
- **Tema**: Elegir un dominio (ej: veterinaria, biblioteca, clínica médica, e-commerce).  
- **Reglas de Negocio**:  
  - Definir al menos 5 reglas operativas (ej: validaciones de stock, restricciones de horarios, límites de reservas).  
  - Ejemplo para una veterinaria:  
    ```plaintext
    1. Una mascota no puede tener más de 3 citas activas en un mismo día.
    2. Los medicamentos vencidos no pueden ser recetados.
    ```

### 2. Modelado de Datos
- **Entidades JPA**:  
  - Mínimo 4 entidades relacionadas (ej: `Cliente`, `Producto`, `Pedido`, `Empleado`).  
  - Diagrama UML/ER con relaciones (`@OneToMany`, `@ManyToOne`).  
- **DTOs**: Usar Data Transfer Objects para todas las operaciones de entrada/salida.

### 3. Implementación de APIs
| **Endpoint**              | **Método** | **Descripción**                     | **Validaciones**                          |
|---------------------------|------------|-------------------------------------|-------------------------------------------|
| `POST /api/clientes`      | POST       | Crear cliente                       | Email válido, teléfono de 10 dígitos      |
| `GET /api/productos`      | GET        | Listar productos en stock           | Filtrar por categoría/disponibilidad      |
| `PUT /api/pedidos/{id}`   | PUT        | Actualizar estado de pedido         | Solo estados permitidos (ej: "En camino") |

### 4. Validaciones Avanzadas
- Anotaciones personalizadas (ej: `@FechaVencimientoValida`).  
- Manejo de errores globales con mensajes claros:  
  ```json
  {
    "timestamp": "2024-10-05T10:00:00",
    "status": 400,
    "error": "Solicitud inválida",
    "details": {
      "email": "Debe ser un correo válido"
    }
  }

### 5. **Ejemplo Estructura del Proyecto**
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/[dominio]/
│   │       ├── controller/   # Controladores REST
│   │       ├── model/        # Entidades JPA
│   │       ├── repository/   # Repositorios Spring Data
│   │       ├── service/      # Lógica de negocio
│   │       ├── dto/         # Data Transfer Objects
│   │       ├── config/      # Configuraciones (Swagger, etc.)
│   │       └── exception/   # Manejo de errores
│   └── resources/
│       ├── application.properties
│       └── data.sql        # Datos iniciales (opcional)
```

### 6. Criterios de Evaluación - Proyecto Spring Boot

## Tabla de Evaluación

| Categoría                     | Peso  | Detalles                                                                 |
|-------------------------------|-------|--------------------------------------------------------------------------|
| **Funcionalidad**             | 30%   | - APIs implementadas cumplen con todas las reglas de negocio definidas<br>- Todos los endpoints funcionan correctamente<br>- Relaciones entre entidades funcionan como se especifica |
| **Validaciones y Excepciones**| 25%   | - Uso adecuado de anotaciones de validación (`@Valid`, `@Pattern`, etc.)<br>- Mensajes de error claros y personalizados<br>- Validación de reglas de negocio en capa de servicio |
| **Documentación**             | 20%   | - `README.md` completo con:<br>  • Descripción del proyecto<br>  • Diagrama de entidades<br>  • Instrucciones de instalación<br>  • Ejemplos de requests/responses<br>- Documentación Swagger/OpenAPI completa<br>- Colección Postman/Insomnia compartida con todos los endpoints |
| **Estructura de Código**      | 15%   | - Uso correcto de DTOs para transferencia de datos<br>- Separación clara en capas (controller, service, repository)<br>- Nombramiento consistente (variables, métodos, clases)<br>- Inyección de dependencias adecuada<br>- Código limpio y bien organizado |
| **Pruebas**                   | 10%   | - Evidencias de pruebas manuales con capturas de pantalla<br>- Colección Postman/Insomnia funcional<br>- Pruebas de happy path y edge cases<br>- Validación de respuestas exitosas y de errores |

## Notas Adicionales

- Copiar el proyecto por medio de un fork
- Entregar por medio de un PR