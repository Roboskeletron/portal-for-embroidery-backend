package ru.vsu.portalforembroidery.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.vsu.portalforembroidery.validator.DesignerProfilePropertiesRequiredValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = DesignerProfilePropertiesRequiredValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DesignerProfilePropertiesRequired {
    String message() default "For designers, experiencedSince and description are required.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
