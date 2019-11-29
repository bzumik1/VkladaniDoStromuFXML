/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyExceptions;

/**
 *
 * @author jakub
 */
public class PromoteException extends RuntimeException{
    public PromoteException(String message){
        super(message);
    }
    public PromoteException(){
        super();
    }
    
}
