package GraphicalUserInterface;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Square extends Rectangle {
    private double side; // CORRECT ON PROPERTY
    public DoubleProperty centerXProperty;
    public DoubleProperty centerYProperty;



    public Square(double size, double stroke){
        super(0,0,size,size);
        //Property settings
        centerXProperty = new SimpleDoubleProperty(0);
        centerXProperty.bind(super.xProperty().add(super.widthProperty().divide(2.0)));
        centerYProperty = new SimpleDoubleProperty(0);
        centerYProperty.bind(super.yProperty().add(super.heightProperty().divide(2.0)));

        super.setArcHeight(size/3);
        super.setArcWidth(size/3);
        super.setStrokeType(StrokeType.INSIDE);
        super.setStroke(Color.BLACK);
        super.setFill(Color.rgb(0, 0, 0, 0));
        super.setStrokeWidth(stroke);
    }

    public double getSide(){
        return side;
    }

    public void setSide(double side){
        this.side = side;
    }

    public void bindCenterXProperty(){

    }

}
