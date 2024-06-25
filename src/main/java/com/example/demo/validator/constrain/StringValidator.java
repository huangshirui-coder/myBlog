package com.example.demo.validator.constrain;

import com.example.demo.validator.annotion.StringNotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class StringValidator implements ConstraintValidator<StringNotNull, String> {
    @Override
    public void initialize(StringNotNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(string)){
            return false;
        }else {
            return true;
        }

    }
}
