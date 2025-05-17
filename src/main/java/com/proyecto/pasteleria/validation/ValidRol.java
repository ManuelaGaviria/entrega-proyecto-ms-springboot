package com.proyecto.pasteleria.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RolValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRol {
    String message() default "Rol no válido. Debe ser 'Producción', 'Despacho' o 'Administrador'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
