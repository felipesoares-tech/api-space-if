package br.com.felipesoarestech.api.spaceif.domain.exception;

public class NotAllowedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NotAllowedException(String message){
        super(message);
    }
}