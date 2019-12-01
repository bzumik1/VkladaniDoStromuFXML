package algorithm;

//IMPORT
import myExceptions.*;

import java.util.*;

/**
 *
 * @author Jakub Znamenacek, Petr Musil
 */
public class Node implements Comparable<Node>{
//ATTRIBUTES
    public static final int ORDER = 4;
    private List<Node> children; //list of children
    private Node parent; //one node can have only one parent
    private List<DataElement> dataElements; //list of node's data
    
//CONSTRUCTOR
    public Node(DataElement dataElement){ //node has to be created at least with one data element
        children = new ArrayList<>();
        parent = null; // new node is created without parent
        dataElements = new ArrayList<>();
        add(dataElement);
    }

//GETTERS AND SETTERS
    /**
     * returns list of children of this node
     *
     * @return      the image at the specified URL
     */
    public List<Node> getChildren(){
        return children;
    }

    /**
     * method to set children of this node
     *
     * @param children list of children to be set as children of this node
     */
    public void setChildren(List<Node> children) {
        this.children = children;
    }

    /**
     * function to get Parent of this node
     *
     * @return      returns node (parent) of this node
     */
    public Node getParent(){
        return parent;
    }

    /**
     * function to set parent of this node
     *
     * @param  parent  node (parent) to be set as parent of this node
     */
    public void setParent(Node parent){
        this.parent = parent;
    }

    /**
     * function to get dataElements of this Node as list
     *
     * @return      list of dataElements
     */
    public List<DataElement> getDataElements(){
        return dataElements;
    }

    /**
     * method to set dataElements of this Node as list
     *
     * @param dataElements list of dataElements to be set as dataElements of this node
     */
    public void setDataElements(List<DataElement> dataElements) {
        this.dataElements = dataElements;
    }

//METHODS
    /**
     * function to add dataElement to this node
     *
     * @param  dataElement  dataElement to be added
     * @return      true if the dataElement was successfully added
     */
    public final boolean add(DataElement dataElement){
        if(!isFull()){ //check if the dataElements list is full
            dataElements.add(dataElement);
            Collections.sort(dataElements);
            return true;
        }
        else{
            throw new DataElementAddException("Can not add data, node is full.");
        }
    }

    /**
     * function to add node (child) to this node
     *
     * @param  child  node (child) to be added
     * @return      returns true if the node (child) was added
     */
    public boolean addChild(Node child){
        if(!childrenIsFull()){ //checks if the node isn't full of children and checks that number of children is maximal same as number of dataElements
            child.setParent(this); //set parent for new child
            children.add(child);
            Collections.sort(children); //sort children
            return true;
        }
        else{
            throw new ChildAddException("Node has already "+ORDER+" children or number of children equals number of dataElements!");
        }
    }

    /**
     * finds out if this node has any children
     *
     * @return      returns true if this node has at least one child
     */
    public boolean hasChildren(){
        return !children.isEmpty();
    }

    /**
     * finds out if the node has parent
     *
     * @return      true if this node has parent
     */
    public boolean hasParent(){
        return parent != null;
    }

    /**
     * delete last child of this node
     *
     * @return      deleted node (child)
     */
    public Node deleteLastChild() {
        if(children.size()>0){ //checks if node has at least one children
            var tempNode = children.get(children.size()-1);
            children.remove(children.size()-1);
            return tempNode;
        }
        else{
            throw new DeleteLastChildException("This node has no children!");
        }
    }

    /**
     * returns child with the biggest dataElement of this node that has smaller dataElement than inserted dataElement
     *
     * @param  dataElement  dataElement for comparison
     * @return      the image at the specified URL
     */
    public Node chooseChild(DataElement dataElement){
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
        throw new ChoseChildException("The correct child was not found!");
    }

    /**
     * function to discover if this node is full
     *
     * @return      true if the number of dataElements is bigger than (or equal) to order
     */
    public boolean isFull(){
        return dataElements.size() >= (ORDER-1);
    }

    /**
     * function to discover if the node is empty
     *
     * @return      returns true if this node has no dataElement
     */
    public boolean isEmpty(){
        return dataElements.isEmpty();
    }

    /**
     * function to discover if the maximum number of children of this node has been reached
     *
     * @return      returns true if number of children of this node is bigger or equal to order (or bigger than number of dataElement)
     */
    private boolean childrenIsFull(){
        return children.size() >= (ORDER) || children.size()>dataElements.size();
    }

    /**
     * function to get number of children of this node
     *
     * @return      number of children of this node as integer
     */
    public int numberOfChildren(){
        return children.size();
    }

    /**
     * function to get biggest dataElement of this node
     *
     * @return      biggest dataElement of this node as DataElement
     */
    public DataElement maxDataElement(){
        return dataElements.get(dataElements.size()-1);
    }

    /**
     * function to delete last data element
     *
     * @return      returns last (deleted) dataElement
     */
    public DataElement deleteLastDataElement(){
        if(!isEmpty()){
            var temp = dataElements.get(dataElements.size() - 1); // save item
            dataElements.remove(dataElements.size()-1); // disconnect it
            return temp; // return item
        }
        throw new DeleteLastDataElementException("Node is empty!");
    }

    /**
     * function to promote dataElement if the node is full and you are trying to add another dataElement into it
     *
     * @return      returns promoted (deleted) dataElement
     */
    public DataElement promote(){
        if(isFull()){ //can be promoted only when node is full
            var temp = dataElements.get(1); //saves the middle element
            dataElements.set(1, dataElements.get(2)); //copy the last element to second place
            dataElements.remove(2);
            return temp;
        }
        throw new PromoteException("Node is not full, can not be promoted!");
    }

    /**
     * function which returns dataElements of this node as String
     *
     * @return      String of dataElements of this node
     */
    public String nodeDataElementsToString(){
        StringBuilder temp = new StringBuilder();
        for(DataElement element:dataElements){
            temp.append("[").append(element.identifier).append("]");
        }
        return temp.toString();
    }

    /**
     * function to return siblings (another children (nodes) of parent) of this node
     *
     * @return      list of siblings of this node
     */
    public List<Node> siblings(){
        if(hasParent()){
            return getParent().getChildren();
        }
        throw new GetSiblingsException("This node has no parent!");
    }


    //PROBABLY DELETE
    public int childrenDataElementsNumber(){
        int temp = 0;
        for(Node child:children){
            temp+=child.dataElements.size();
        }
        return temp;
    }
    
    @Override
    public String toString(){
        StringBuilder help = new StringBuilder();
        help.append("Node [");
        help.append("ORDER:" + ORDER + " , ");
        help.append("childrenNumber:").append(children.size()).append(" , ");
        help.append("dataElementsNumber:").append(dataElements.size()).append(" , ");
        help.append("dataElements:");
        for(DataElement element:dataElements){
            help.append("[").append(element).append("]");
        }
        help.append("]");
        
        return help.toString();
    }

    @Override
    public int compareTo(Node o) {
        
        return maxDataElement().compareTo(o.maxDataElement());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(children, node.children) &&
                Objects.equals(parent, node.parent) &&
                dataElements.equals(node.dataElements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children, parent, dataElements);
    }
}
