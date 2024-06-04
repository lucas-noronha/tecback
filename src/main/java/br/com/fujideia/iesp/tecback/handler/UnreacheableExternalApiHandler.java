package br.com.fujideia.iesp.tecback.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnreacheableExternalApiHandler {

    @ExceptionHandler(UnreacheableExternalApiException.class)
    public void handleCustomException(UnreacheableExternalApiException ex) {
        System.err.println("Exceção personalizada capturada: " + ex.getMessage());
    }
}
