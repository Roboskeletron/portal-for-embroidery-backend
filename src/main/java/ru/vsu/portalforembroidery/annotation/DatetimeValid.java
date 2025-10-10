package ru.vsu.portalforembroidery.annotation;

import ru.vsu.portalforembroidery.validator.DatetimeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatetimeValidator.class)
public @interface DatetimeValid {

    String message() default "Datetime invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
