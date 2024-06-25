package com.example.demo.validator.constrain;

import com.example.demo.validator.annotion.LongNotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 判断Long是否为空【校验器】
 */
public class LongValidator implements ConstraintValidator<LongNotNull, Long> {

    @Override
    public void initialize(LongNotNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        if (aLong == null){
            return false;
        }
        return true;
    }
}
