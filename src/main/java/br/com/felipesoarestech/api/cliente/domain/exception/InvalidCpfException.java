package br.com.felipesoarestech.api.cliente.domain.exception;

public class InvalidCpfException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public InvalidCpfException(String message){
        super(message);
    }
}
