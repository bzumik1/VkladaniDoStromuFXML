/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyExceptions;

/**
 *
 * @author lukasznamenacek
 */
public class DeleteLastChildException extends RuntimeException{
    public DeleteLastChildException(){
        super();
    }
    public DeleteLastChildException(String message){
        super(message);
    }
    
}
