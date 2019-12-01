module znamenacek.jakub.fs.cvut {
    requires javafx.controls;
    requires javafx.fxml;

    opens znamenacek.jakub.fs.cvut to javafx.fxml;
    exports znamenacek.jakub.fs.cvut;
}