package com.example.demo.validator.annotion;

import com.example.demo.base.validator.Messages;
import com.example.demo.validator.constrain.IdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 效验Id【注解】
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IdValidator.class})
public @interface IdValid {
    boolean required() default true;

    String message() default Messages.CK_NOT_NULL_DEFAULT;

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
