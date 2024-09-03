package br.edu.iftm.rastreamento.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(NaoAcheiException.class)
    public ResponseEntity<String> handleNaoAcheiException(NaoAcheiException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
