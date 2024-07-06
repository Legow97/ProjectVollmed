package med.voll.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class catchError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treatError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity treatError400(MethodArgumentNotValidException e){
        var error = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();

        return ResponseEntity.badRequest().body(error);
    }

    private record DataErrorValidation(String campo, String error){
        public DataErrorValidation(FieldError fieldError){
            this(fieldError.getField(),fieldError.getDefaultMessage());
        }
    }
}
