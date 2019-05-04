package com.aiolos.demo.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Aiolos
 * @date 2019-05-02 10:56
 */
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        log.info("validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        log.info("valid value -> {}", value);
        return true;
    }
}
