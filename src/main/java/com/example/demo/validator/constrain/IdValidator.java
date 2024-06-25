package com.example.demo.validator.constrain;

import com.example.demo.validator.annotion.IdValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 效验Id【校验器】
 */
public class IdValidator implements ConstraintValidator<IdValid, Integer> {
    @Override
    public void initialize(IdValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null){
            return false;
        }
        return true;
    }
}
