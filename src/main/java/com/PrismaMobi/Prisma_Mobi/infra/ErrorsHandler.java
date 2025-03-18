package com.PrismaMobi.Prisma_Mobi.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<String> unauthorizedUser(AccessDeniedException e){
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFound(){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> validationError400 (MethodArgumentNotValidException e){
        var error = e.getFieldErrors();
        return ResponseEntity.badRequest().
                body(error.stream().map(ValidationError::new).toList());
    }


    public record ErrorResponse(int status, String message, LocalDateTime timestamp) {
    }

    public record ValidationError(String field, String message){
        public ValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
