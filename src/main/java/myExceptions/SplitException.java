package myExceptions;

public class SplitException extends RuntimeException{
    public SplitException(String message){
        super(message);
    }

    public SplitException(){
        super();
    }
}
