package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.validations;

public class RequestError {
    private String field;
    private String error;

    public RequestError(String field, String error){
        this.field = field;
        this.error = error;
    }

    public String getField(){
        return field;
    }

    public String getError(){
        return error;
    }
}
