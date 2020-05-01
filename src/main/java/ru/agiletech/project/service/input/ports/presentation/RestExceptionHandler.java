package ru.agiletech.project.service.input.ports.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.agiletech.project.service.domain.project.ProjectAlreadyExistsException;
import ru.agiletech.project.service.infrastructure.persistence.ProjectNotFoundException;
import ru.agiletech.project.service.infrastructure.persistence.RepositoryAccessException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    private ResponseEntity<String> catchValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors =  bindingResult.getAllErrors();

        StringBuilder reason = new StringBuilder();

        errors.forEach(error -> reason.append(error.getDefaultMessage())
                .append(";"));

        log.error(reason.toString());

        return new ResponseEntity<>(reason.toString(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectAlreadyExistsException.class)
    private ResponseEntity<String> catchProjectAlreadyExistsException(ProjectAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> catchIllegalArgumentException(IllegalArgumentException ex){
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    private ResponseEntity<String> catchUnsupportedOperationException(UnsupportedOperationException ex){
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RepositoryAccessException.class)
    private ResponseEntity<String> catchInfrastructureExceptions(RepositoryAccessException ex){
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    private ResponseEntity<String> catchNotFoundException(ProjectNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

}
