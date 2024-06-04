package br.com.fujideia.iesp.tecback.validator;

import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DataValidadeValidator implements ConstraintValidator<DataValidade, Date> {
    private Date min;

    @Override
    public void initialize(DataValidade constraint) {
        min = new Date();
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return value.after(min);
    }
}
