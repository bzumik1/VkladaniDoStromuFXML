package znamenacek.jakub.fs.cvut;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import Algorithm.Tree;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Tree tree;
    private static int[]  dataElementArray ={3,7,4,9,10,0,5,6,8,2,1,20,15,14,12}; //RENAME TO IDENTIFIER ARRAY?
    private static int currentState =0;
    public static Scene scene; //SHOULD IT BE PUBLIC??

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }


    public static Tree getTree() {
        return tree;
    }

    public static void setTree(Tree tree) {
        App.tree = tree;
    }

    public static int[] getDataElementArray() {
        return dataElementArray;
    }

    public static void setDataElementArray(int[] dataElementArray) {
        App.dataElementArray = dataElementArray;
    }

    public static int getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(int currentState) {
        App.currentState = currentState;
    }

    public static void increaseCurrentState(){
        if(currentState<dataElementArray.length-1){
            currentState++;
        }
    }

    public static void decreaseCurrentState(){
        if(currentState>0){
            currentState--;
        }
    }
}

