/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myExceptions;

/**
 *
 * @author jakub
 */
public class ChildAddException extends RuntimeException{
    public ChildAddException(String message){
        super(message);
    }
    
    public ChildAddException(){
        super();
    }
}
