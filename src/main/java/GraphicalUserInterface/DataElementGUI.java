package GraphicalUserInterface;

import Algorithm.DataElement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DataElementGUI extends StackPane {
    private Text identifier;
    private Square boundary;
    private double xDimension;

    public DataElementGUI (DataElement dataElement){
        super();
        super.setAlignment(Pos.CENTER);
        xDimension = 50;
        boundary = new Square(xDimension,2);
        identifier = new Text(dataElement.identifierToString());
        super.getChildren().addAll(identifier,boundary);

    }

    public Text getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Text identifier) {
        this.identifier = identifier;
    }

    public Square getBoundary() {
        return boundary;
    }

    public void setBoundary(Square boundary) {
        this.boundary = boundary;
    }

    public double getXDimension() {
        return xDimension;
    }
}
