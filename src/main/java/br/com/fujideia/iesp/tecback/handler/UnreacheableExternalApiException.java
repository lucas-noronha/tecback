package br.com.fujideia.iesp.tecback.handler;

public class UnreacheableExternalApiException extends RuntimeException {
    public UnreacheableExternalApiException(String message) {
        super(message);
    }
}
