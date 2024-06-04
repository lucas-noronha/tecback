package br.com.fujideia.iesp.tecback.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LengthValidator implements ConstraintValidator<Length, Integer> {
    private Integer min;
    private Integer max;

    @Override
    public void initialize(Length constraint) {
        min = constraint.min();
        max = constraint.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return value >= min && value <= max;
    }
}
