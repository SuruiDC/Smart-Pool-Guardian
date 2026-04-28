package pe.upc.smartpoolguardian.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Control de error en los @Valid de los controllers
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>handleValid(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((error)->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    //Control de error de un JSON inválido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonError(HttpMessageNotReadableException ex){
        return ResponseEntity.
                badRequest().
                body(Map.of(
                        "error","JSON inválido",
                        "message", ex.getMostSpecificCause().getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    //Control de un error general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(Map.of(
                        "error","Error global",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }
}
