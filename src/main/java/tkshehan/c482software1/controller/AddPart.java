package tkshehan.c482software1.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tkshehan.c482software1.model.Part;

import java.io.IOException;

public class AddPart {
    private ObservableList<Part> partsList;

    public void setPartsList(ObservableList<Part> partsList) {
        this.partsList = partsList;
    }
    public void savePart(ActionEvent actionEvent) {
    }

    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tkshehan/c482software1/main.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 480);
        stage.setScene(scene);
        stage.show();
    }
}
