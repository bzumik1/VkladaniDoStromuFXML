package graphicalUserInterface;

import algorithm.DataElement;
import javafx.geometry.Pos;
import algorithm.Node;
import javafx.scene.layout.HBox;

public class NodeGUI extends HBox {
    private double xDimension;
    private double yDimension;

    public NodeGUI(Node node){
        super();
        xDimension = 0;
        double spacing = 1;
        super.setSpacing(spacing);
        super.setAlignment(Pos.CENTER);
        createNode(node);
    }

    private void createNode(Node node){
        for(DataElement dataElement: node.getDataElements()){
            var dataElementGUI = new DataElementGUI(dataElement);
            xDimension += dataElementGUI.getBoundary().getWidth()+super.getSpacing(); // CORRECT EDIT ...TWO PROBLEMS
            yDimension = dataElementGUI.getBoundary().getWidth(); //CORRECT ... TWO PROBLEMS ADDED MANY TIMES
            super.getChildren().add(dataElementGUI);
        }
        xDimension -= super.getSpacing(); //DELETE LAST SPACING MAYBE CORRECT
    }

    public double getxDimension() {
        return xDimension;
    }

    public double getyDimension() {
        return yDimension;
    }
}

