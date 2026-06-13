package com.tienda.Tienda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ProductoNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error","No encontrado",
                "mensaje",ex.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        ));
    }
    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(CategoriaNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error","No encontrado",
                "mensaje",ex.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        ));
    }

    @ExceptionHandler (Exception.class)
    public ResponseEntity<Map<String,Object>> handleGeneral(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error","Error interno",
                "mensaje",ex.getMessage(),
                "timestamp",LocalDateTime.now().toString()
        ));
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex){
        var errores = new HashMap<String,String>();
        ex.getBindingResult().getAllErrors().forEach( error ->{
            String campo = ((FieldError)error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo,mensaje);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error","Validación fallida",
                "campos",errores,
                "timestamp", LocalDateTime.now().toString()
        ));
    }
}
