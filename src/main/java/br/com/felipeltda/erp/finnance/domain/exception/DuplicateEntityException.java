package br.com.felipeltda.erp.finnance.domain.exception;
public class DuplicateEntityException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public DuplicateEntityException(String message){
        super(message);
    }
}
