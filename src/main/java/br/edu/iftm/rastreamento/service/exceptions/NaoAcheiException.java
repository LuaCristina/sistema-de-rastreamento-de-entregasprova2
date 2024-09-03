package br.edu.iftm.rastreamento.service.exceptions;

public class NaoAcheiException extends RuntimeException {

    public NaoAcheiException(Long id) {
        super("Recurso com ID " + id + " não encontrado.");
    }

    public NaoAcheiException(String message) {
        super(message);
    }
}