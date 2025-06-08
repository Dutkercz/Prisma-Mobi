package com.PrismaMobi.Prisma_Mobi.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> duplicateEntries(SQLIntegrityConstraintViolationException e) {
        var erro = e.getMessage();
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFound() {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> validationError400(MethodArgumentNotValidException e) {
        var error = e.getFieldErrors();
        return ResponseEntity.badRequest().
                body(error.stream().map(ValidationError::new).toList());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> invalidCredentials(BadCredentialsException e) {
        System.out.println("Errrou a sanha jovi " + e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> badRequest(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    public record ErrorResponse(int status, String message, LocalDateTime timestamp) {
    }

    public record ValidationError(String field, String message) {
        public ValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
