package myExceptions;

public class GetSiblingsException extends RuntimeException {
    public GetSiblingsException(String message){
        super(message);
    }

    public GetSiblingsException(){
        super();
    }
}
