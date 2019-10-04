package com.carros.api.api.exception;

import com.carros.api.domain.CarroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(CarroService.class);

    @ExceptionHandler({
            EmptyResultDataAccessException.class
    })
    public ResponseEntity errorNotFound(Exception ex){
        logger.error("Objeto Não encontrado");
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity errorBadRequest(Exception ex){
        logger.error("Objeto mal formatado");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            AccessDeniedException.class
    })
    public ResponseEntity accessDenied(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionError("Acesso Negado"));
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ExceptionError("Operação não permitida"),HttpStatus.METHOD_NOT_ALLOWED);
    }

    private class ExceptionError implements Serializable {
        private String error;
        public ExceptionError(String error) {
            this.error = error;
        }
        public String getError(){
            return error;
        }
    }

}
