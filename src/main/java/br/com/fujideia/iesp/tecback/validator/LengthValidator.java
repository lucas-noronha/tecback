package br.com.fujideia.iesp.tecback.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LengthValidator implements ConstraintValidator<Length, String> {
    private Integer min;
    private Integer max;

    @Override
    public void initialize(Length constraint) {
        min = constraint.min();
        max = constraint.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return value.length() >= min && value.length() <= max;
    }
}
