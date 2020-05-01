package ru.agiletech.project.service.domain.project;

public class ProjectAlreadyExistsException extends RuntimeException{

    public ProjectAlreadyExistsException() {
    }

    public ProjectAlreadyExistsException(String message) {
        super(message);
    }

    public ProjectAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ProjectAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
