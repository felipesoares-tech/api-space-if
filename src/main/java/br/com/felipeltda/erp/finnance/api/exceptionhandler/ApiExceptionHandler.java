package br.com.felipeltda.erp.finnance.api.exceptionhandler;

import br.com.felipeltda.erp.finnance.domain.exception.DuplicateEntityException;
import br.com.felipeltda.erp.finnance.domain.exception.EmailAlreadyExistsException;
import br.com.felipeltda.erp.finnance.domain.exception.EntityNotFoundException;
import br.com.felipeltda.erp.finnance.domain.exception.InvalidCpfException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<?> tratarEntidadeDuplicada(DuplicateEntityException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT,request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontrada(EntityNotFoundException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleDuplicateEmail(EmailAlreadyExistsException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT,request);
    }

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<?> handleInvalidCpf(InvalidCpfException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (e instanceof MethodArgumentNotValidException) {
            // Tratamento personalizado para a exceção MethodArgumentNotValidException

            Map<String, String> errors = new HashMap<>();
            ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });

            // Crie uma instância de Problem com os detalhes desejados
            body = Problem.builder()
                    .dataHora(OffsetDateTime.now())
                    .message("Erro de validação")
                    .detalhes(errors)
                    .build();

            status = HttpStatus.BAD_REQUEST;
        } else {
            // Tratamento para outras exceções
            if (body == null) {
                body = Problem.builder()
                        .dataHora(OffsetDateTime.now())
                        .message(status.toString())
                        .build();
            } else if (body instanceof String) {
                body = Problem.builder()
                        .dataHora(OffsetDateTime.now())
                        .message((String) body)
                        .build();
            }
        }

        return super.handleExceptionInternal(e, body, headers, status, request);
    }

}
