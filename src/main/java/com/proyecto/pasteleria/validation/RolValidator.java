package com.proyecto.pasteleria.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class RolValidator implements ConstraintValidator<ValidRol, String> {

    private static final Set<String> ROLES_VALIDOS = Set.of(
            "Producción", "Despacho", "Administrador"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && ROLES_VALIDOS.contains(value);
    }
}
