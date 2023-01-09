package itidigital.backendchallenge.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionType {

    INVALID_DATA("Invalid Input"),
    RESOURCE_NOT_FOUND("Resource not found"),
    PROCESSING_ERROR("Error processing validation rule(s)."),
    SYSTEM_ERROR("System Error");

    private String title;

    ExceptionType( String title){
        this.title = title;
    }

}
