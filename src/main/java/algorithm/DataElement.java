/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;



/**
 *
 * @author jakub
 */
public class DataElement implements Comparable<DataElement>{
//ATTRIBUTES
    int identifier;
//CONSTRUCTOR
    public DataElement(int identifier){
        this.identifier = identifier;
    }
    public DataElement(){
        this.identifier = 0;
    }

//GETTERS AND SETTERS
    /**
     * method to get identifier of this dataElement
     *
     * @return      identifier of this dataElement
     */
    public int getIdentifier(){
        return identifier;
    }

    /**
     * method to set identifier of this dataElement
     *
     * @param identifier identifier to be set as identifier of this dataElement
     */
    public void setIdentifier(Integer identifier){
        this.identifier = identifier;
    }

//METHODS
    /**
     * method to return identifier as String
     *
     * @return      returns identifier as String
     */
    public String identifierToString(){
        return ""+identifier;
    }
    
    @Override
    public String toString(){
        return "Data ["+identifier+"]";
    }
    
    //implementing Comparable interface to be able to sort
    @Override
    public int compareTo(DataElement o) {
        Integer identifierThis = identifier;
        Integer identifierIn = o.getIdentifier();
        return identifierThis.compareTo(identifierIn);
    }
}
