package com.example.demo.validator.constrain;

import com.example.demo.validator.annotion.IntNotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntValidator implements ConstraintValidator<IntNotNull, Integer> {
    @Override
    public void initialize(IntNotNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null){
            return false;
        }else {
            return false;
        }

    }
}
