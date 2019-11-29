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
public class DataElementAddException extends RuntimeException {
//CONSTRUCTOR
    public DataElementAddException(String message){
        super(message);
    }
    public DataElementAddException(){
        super();
    }
    
}
