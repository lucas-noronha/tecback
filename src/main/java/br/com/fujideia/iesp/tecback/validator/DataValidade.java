package br.com.fujideia.iesp.tecback.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DataValidadeValidator.class)
public @interface DataValidade {

    String message() default "A data deve ser maior do que hoje!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
