package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.controller.exceptions;

import com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> resourceNotFound (
            ResourceNotFoundException exception,
            HttpServletRequest request)
    {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Resource not found";
        StandartError stdError = new StandartError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(stdError);

    }
}
