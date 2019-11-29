/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

//IMPORT
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
    
//METHODS
    public void add(int identifier){ // create empty dataElement in tree with given identifier
        add(new DataElement(identifier));
    }

    public void add(DataElement dataElement){
        var currentNode = root;
        while(currentNode.hasChildren()){ //finds last corect node
            currentNode = currentNode.choseChild(dataElement);
        }
        //current node is not full
        if(!currentNode.isFull()){ //adds dataElement into corect node
            currentNode.add(dataElement);
        }
        //current node is full, split algorithm have to start
        else{
            split(currentNode,dataElement);
        }
    }
    
    private void split(Node thisNode, DataElement data){ // split the node
        //EXCEPTION HAS TO BE CALLED ONLY WHEN NODE IS FULL
        //Node is root
	if(!thisNode.hasParent()){ 
            root = new Node(thisNode.promote()); // create new node (root) with dataElement promote (middle dataElement from old Node is deleted)
            depth++;
            var rightNode = new Node(thisNode.deleteLastDataElement()); //Copy and delete last dataElement from current node
            //Connect Nodes to new Root;
            root.addChild(thisNode);
            root.addChild(rightNode);
            //Connect Children to right node and disconect them from thisNode
            for(int i = thisNode.getChildrenNumber();i>2;i--){ //Maximal last two children from first Node are deleted and moved to new one
                rightNode.addChild(thisNode.deleteLastChild());
            }
            //Add dataElement to tree
            root.choseChild(data).add(data);
        }
        //Node is not root
        else{
            //Parent of thisNode is not full, so promote can be saved to it
            var parent = thisNode.getParent();
            if(!parent.isFull()){ 
               parent.add(thisNode.promote()); //Promote is saved to parent
               var rightNode = new Node(thisNode.deleteLastDataElement()); //last element is used to create right node
               //Connect new Node to parent
               parent.addChild(rightNode);
               //Reconect children to rightNode
               for(int i = thisNode.getChildrenNumber();i>2;i--){ //Maximal last two children (2,3) from first Node are deleted and moved to new one
                   rightNode.addChild(thisNode.deleteLastChild());
               }
               //Add dataElement to tree
                parent.choseChild(data).add(data);
               
            }
            //Parent of thisNode is full, needs to be splited -> recursive use of split function
            else{
                split(parent,thisNode.promote());
                var rightNode = new Node(thisNode.deleteLastDataElement()); //last element is used to create right node
               //Connect new Node to parent
               parent.addChild(rightNode);
               //Reconect children to rightNode
               for(int i = thisNode.getChildrenNumber();i>2;i--){ //Maximal last two children from first Node are deleted and moved to new one
                   rightNode.addChild(thisNode.deleteLastChild());
               }
               //Add dataElement to tree
                parent.choseChild(data).add(data);
            }
        }
    }
    

    
    public Node getRoot(){
        return root;
    }

    public int getDepth(){
        return depth;
    }
    
    @Override
    public String toString(){
        var tempString = root.nodeDataElemnetsToString()+"\n"; //first row as String
        List<Node> childrenAtOneLevel = new ArrayList<>(root.getChildren());
        List<Node> tempChildrenAtOneLevel = new ArrayList<>();

        for(int i=0;i<depth;i++){ //goes through all rows
            for(Node child: childrenAtOneLevel){
                tempString += child.nodeDataElemnetsToString()+"   ";
                for(Node grandChild: child.getChildren()){
                    tempChildrenAtOneLevel.add(grandChild);
                }
            }
            tempString += "\n"; //end of the row
            childrenAtOneLevel = tempChildrenAtOneLevel;
            tempChildrenAtOneLevel = new ArrayList<>();
        }
        return tempString;
    }
    
}


