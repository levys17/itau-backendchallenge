package itidigital.backendchallenge.exceptionhandler.exceptions;

public class RuleProcessingException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    public RuleProcessingException(String mensagem) {
        super(mensagem);
    }
}
