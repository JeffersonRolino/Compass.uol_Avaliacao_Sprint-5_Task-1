package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.validations;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.entities.Sale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateValidator implements ConstraintValidator<ValidDates, Sale> {
    @Override
    public void initialize(ValidDates constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Sale sale, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime creationDate = sale.getCreationDate();
        LocalDateTime localDateTime = sale.getExpirationDate();

        return creationDate.isBefore(localDateTime);
    }
}
