package Algorithm;

//IMPORT
import MyExceptions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jakub
 */
public class Node implements Comparable<Node>{
//ATTRIBUTES
    static final int ORDER = 4;
    private List<Node> children; //list of childrens
    private Node parent; //one node can have only one parent
    private List<DataElement> dataElements; //list of node's data
    
//CONSTRUCOR
    public Node(DataElement dataElement){ //node has to be created at least with one data element
        children = new ArrayList<>();
        parent = null; // new node is created without parent
        dataElements = new ArrayList<>();
        add(dataElement);
    }
    
//METHODS
    public final void add(DataElement dataElement){
        if(!isFull()){ //check if the dataElements list is full
            dataElements.add(dataElement);
            Collections.sort(dataElements);
        }
        else{
            throw new DataElementAddException("Can not add data, node is full.");
        }
    }
    
    public void addChild(Node child){
        if(!childrenIsFull() && children.size()<=dataElements.size()){ //checks if the node isn't full of children and checks their number
            children.add(child);
            children.get(children.size()-1).setParent(this); //set parent for new child
            Collections.sort(children); //sort children
        }
        else{
            throw new ChildAddException("Node has already "+ORDER+" children!");
        }
    }
    
    public boolean hasChildren(){
        return !children.isEmpty();
    }
    
    public boolean hasParent(){
        return parent != null;
    }
    
    public List<Node> getChildren(){
        return children;
    }
    
    public Node deleteLastChild() {
        if(children.size()>0){
            var tempNode = children.get(children.size()-1);
            children.remove(children.size()-1);
            return tempNode;
        }
        else{
            throw new DeleteLastChildException("This node has no children!");
        }
    }
    
    public Node choseChild(DataElement dataElement){
        switch(children.size()){
            case 4:
                if(dataElement.getIdentifier() > dataElements.get(2).getIdentifier()){
                    return children.get(3);
                }
            case 3:
                if(dataElement.getIdentifier() > dataElements.get(1).getIdentifier()){
                    return children.get(2);
                }
            case 2:
                if(dataElement.getIdentifier() > dataElements.get(0).getIdentifier()){
                    return children.get(1);
                }
            case 1:
                if(dataElement.getIdentifier() < dataElements.get(0).getIdentifier()){
                    return children.get(0);
                }
        }
        throw new ChoseChildException("The correct child was not chosen!");
    }
    
    public List<DataElement> getDataElements(){
        return dataElements;
    }
    
    public Node getParent(){
        return parent;
    }
    
    private void setParent(Node parent){
        this.parent = parent;
    }

    public boolean isFull(){
        return dataElements.size() >= (ORDER-1);
    }
    
    public boolean isEmpty(){
        return dataElements.isEmpty();
    }
    
    private boolean childrenIsFull(){
        return children.size() >= (ORDER);
    }
    
    public int getChildrenNumber(){
        return children.size();
    }
    
    public DataElement maxDataElement(){
        return dataElements.get(dataElements.size()-1);
    }
    
    public DataElement deleteLastDataElement(){
        if(!isEmpty()){
            var temp = dataElements.get(dataElements.size() - 1); // save item
            dataElements.remove(dataElements.size()-1); // disconnect it
            return temp; // return item
        }
        throw new DeleteLastDataElementException("Node is empty!");
    }
    
    public DataElement promote(){
        if(isFull()){ //can be promoted only when node is full
            var temp = dataElements.get(1); //saves the middle element
            dataElements.set(1, dataElements.get(2)); //copy the last element to second place
            dataElements.remove(2);
            return temp;
        }
        throw new PromoteException("Node is not full, can not be promoted!");
    }
    
    public String nodeDataElemnetsToString(){
        var temp = "";
        for(DataElement element:dataElements){
            temp += "["+element.identifier+"]";
        }
        return temp;
    }
    
    public List<Node> getSiblings(){
        //EXCEPTION OR CORRECTION NEEDED
        return getParent().children;
    }

    //PROBBABLY DELETE
    public int childrensDataElementsNumber(){
        int temp = 0;
        for(Node child:children){
            temp+=child.dataElements.size();
        }
        return temp;
    }
    
    @Override
    public String toString(){
        String help = "";
        help += "Node [";
        help += "ORDER:"+ORDER+" , ";
        help += "childrenNumber:"+children.size()+" , ";
        help += "dataElementsNumber:"+dataElements.size()+" , ";
        help += "dataElements:";
        for(DataElement element:dataElements){
            help +="["+element+"]";
        }
        help += "]";
        
        return help;
    }

    @Override
    public int compareTo(Node o) {
        
        return maxDataElement().compareTo(o.maxDataElement());
    }
        
}
