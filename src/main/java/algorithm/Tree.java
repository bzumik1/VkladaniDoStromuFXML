/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

//IMPORT
import myExceptions.SplitException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class Tree { 
//ATTRIBUTES
    private Node root;
    private int depth;
    
//CONSTRUCTOR
    public Tree(Node root){
        this.root = root;
        depth = 0;
    }
    
    public Tree(DataElement dataElement){
        this.root = new Node(dataElement);
        depth = 0;
    }

    public Tree(int Identifier){
        this.root = new Node(new DataElement(Identifier));
        depth = 0;
    }
//GETTERS AND SETTERS
    /**
     * method to get Root of this tree
     *
     * @return  node (root) of this tree
     */
    public Node getRoot(){
        return root;
    }

    /**
     * method to set Root of this tree
     *
     * @param root node (root) to be set as the new root of this tree
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * method to get depth of this tree
     *
     * @return  depth (integer) of this tree
     */
    public int getDepth(){
        return depth;
    }

    /**
     * method to set depth of this tree
     *
     * @param depth integer (depth) to be set as the new depth of this tree
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

//METHODS
    /**
     * method to add new dataElement just thought identifier
     *
     * @param identifier identifier (integer) for creating new dataElement and adding it to this tree
     */
    public void add(int identifier){ // create empty dataElement in tree with given identifier
        add(new DataElement(identifier));
    }

    /**
     * method to add new dataElement just thought identifier
     *
     * @param dataElement dataElement (DataElement) to be added to this tree
     */
    public void add(DataElement dataElement){
        var currentNode = root;
        if(currentNode.isFull()){ //if root if full, it is split, new root is set as new currentNode
            split(currentNode);
            currentNode = root;
        }
        while(currentNode.hasChildren()){ //finds last correct node
            currentNode = currentNode.chooseChild(dataElement);
            if(currentNode.isFull()){ //split node in way that is full, this can happen only once in way
                split(currentNode);
                currentNode = currentNode.getParent(); // it has to choose right way
            }
        }
        currentNode.add(dataElement);
    }


    private void split(Node thisNode) { // split the node
        //Node is root
        if (!thisNode.hasParent()) {
            root = new Node(thisNode.promote()); // create new node (root) with dataElement promote (middle dataElement from old Node is deleted)
            depth++; //increase the depth of the tree
            root.addChild(thisNode); //add this node as child of the new root
            createRightNode(thisNode,root);
        }
        //Node is not root
        else {
            //Parent of thisNode is not full, so promote can be saved to it
            var parent = thisNode.getParent();
            parent.add(thisNode.promote()); //Promote is saved to parent
            createRightNode(thisNode,parent);
        }
    }


    private void createRightNode(Node thisNode, Node parent){
        var rightNode = new Node(thisNode.deleteLastDataElement()); //Copy and delete last dataElement from current node
        parent.addChild(rightNode);//connect right node to parent
        //Connect Children to right node and disconnect them from thisNode
        for (int i = thisNode.numberOfChildren(); i > 2; i--) { //Maximal last two children from first Node are deleted and moved to new one
            rightNode.addChild(thisNode.deleteLastChild());
        }
    }


    

    
    @Override
    public String toString(){
        //using stringBuilder because whenever you try to add something to String it has to create new object, this is not the case of stringBuilder
        StringBuilder stringBuilder = new StringBuilder(root.nodeDataElementsToString()+"\n"); //first row as String
        List<Node> childrenAtOneLevel = new ArrayList<>(root.getChildren());
        List<Node> tempChildrenAtOneLevel = new ArrayList<>();

        for(int i=0;i<depth;i++){ //goes through all rows
            for(Node child: childrenAtOneLevel){
                stringBuilder.append(child.nodeDataElementsToString());
                stringBuilder.append("   ");
                tempChildrenAtOneLevel.addAll(child.getChildren());
            }
            stringBuilder.append("\n"); //end of the row
            childrenAtOneLevel = tempChildrenAtOneLevel;
            tempChildrenAtOneLevel = new ArrayList<>();
        }
        return stringBuilder.toString();
    }
    
}


