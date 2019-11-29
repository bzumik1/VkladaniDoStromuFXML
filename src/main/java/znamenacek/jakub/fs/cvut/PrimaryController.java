package znamenacek.jakub.fs.cvut;

import java.io.IOException;
import Algorithm.DataElement;
import Algorithm.Tree;
import GraphicalUserInterface.DataElementGUI;
import GraphicalUserInterface.TreeGUI;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class PrimaryController {

    @FXML
    private VBox root ;

    @FXML
    private HBox nextDataElementHBox;

    @FXML
    private HBox nextDataElementLabelHBox;


    @FXML
    private void showTreeAtStart() throws IOException {
        App.setCurrentState(0);
        showTree(App.getCurrentState());

        App.scene.widthProperty().addListener((obs, oldVal, newVal) -> { //SHOULD BE APPLIED ONLY ONES NOT HERE
            refreshNextDataElement();
        });



    }

    @FXML
    private void showWholeTree() throws IOException {
        App.setCurrentState(App.getDataElementArray().length-1);
        showTree(App.getCurrentState());

    }

    @FXML
    private void showNextStep() throws IOException {
        App.increaseCurrentState();
        showTree(App.getCurrentState());

    }

    @FXML
    private void showPreviousStep() throws IOException {
        App.decreaseCurrentState();
        showTree(App.getCurrentState());


    }

    private void showTree(int index){ //show tree to given index (including)
        App.setTree(new Tree(App.getDataElementArray()[0]));
        if (index>0){
            for(int i=1;i<= index;i++){
                App.getTree().add(App.getDataElementArray()[i]);
            }
        }

        root.getChildren().clear(); //delete old tree from screen
        root.getChildren().add(new TreeGUI(App.getTree().getRoot())); //add new tree to screen
        refreshNextDataElement();
    }

    private void refreshNextDataElement(){
        nextDataElementHBox.getChildren().clear(); //delete all elements from upcoming elements
        double width =2*50+20; //REPLACE WITH VARIABLE
        int lastAddedDataElementIdentifier=0; //doesnt matter what is lastAddedDataElementIdentifier at beginning
        double widthMax = App.scene.getWidth()-nextDataElementLabelHBox.getWidth();
        for(int i =(App.getCurrentState()+1); (i<App.getDataElementArray().length)&&(width < (widthMax));i++){ //REPLACE WITH VARIABLE
            width += nextDataElementHBox.getSpacing()+50; //REPLACE CONSTANT WITH VARIABLE
            lastAddedDataElementIdentifier=App.getDataElementArray()[i];
            nextDataElementHBox.getChildren().add(new DataElementGUI(new DataElement(App.getDataElementArray()[i])));
        }

        if((width > widthMax)&&(lastAddedDataElementIdentifier!=App.getDataElementArray()[App.getDataElementArray().length-1])){
            var ellipsis = new Label("...");
            ellipsis.setAlignment(Pos.TOP_LEFT);
            ellipsis.setFont(new Font("System",48));
            nextDataElementHBox.getChildren().add(ellipsis);
        }

    }



}
