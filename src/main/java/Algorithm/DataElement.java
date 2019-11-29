/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

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
//METHODS
    public int getIdentifier(){
        return identifier;
    }

    public String identifierToString(){
        return ""+identifier;
    }
    
    @Override
    public String toString(){
        return "Data ["+identifier+"]";
    }
    
    //implementing Comparable interface to be abble for sorting
    @Override
    public int compareTo(DataElement o) {
        Integer identifierThis = identifier;
        Integer identifierIn = o.getIdentifier();
        return identifierThis.compareTo(identifierIn);
    }
}
