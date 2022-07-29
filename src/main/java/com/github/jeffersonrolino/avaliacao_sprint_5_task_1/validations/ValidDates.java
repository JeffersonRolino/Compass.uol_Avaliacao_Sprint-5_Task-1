package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class )
public @interface ValidDates {
    String message() default "Date of Creation must be inferior a Expiration Date.";
    Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};
}
