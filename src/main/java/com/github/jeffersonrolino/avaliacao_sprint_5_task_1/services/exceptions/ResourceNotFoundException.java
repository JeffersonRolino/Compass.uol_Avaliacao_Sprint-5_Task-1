package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id){
        super("Resource Not Found. Id " + id);
    }
}
