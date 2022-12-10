package tkshehan.c482software1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tkshehan.c482software1.model.InhousePart;
import tkshehan.c482software1.model.Part;

import java.io.IOException;

public class AddPart {

    public RadioButton inHouse;
    public RadioButton outsourced;
    public Label sourceLabel;
    public TextField nameTF;
    public TextField invTF;
    public TextField priceTF;
    public TextField minTF;
    public TextField maxTF;
    public TextField originTF;
    public Button exitButton;

    public void savePart(ActionEvent actionEvent) {
        String errorMessage = "";
        String name = nameTF.getText().trim();
        int inventory = 0;
        double price  = 0;
        int min  = 0;
        int max = 0;
        String origin = originTF.getText().trim();
        int machineId = 0;

        try {
            inventory = Integer.parseInt(invTF.getText());
            min = Integer.parseInt(minTF.getText());
            max = Integer.parseInt(maxTF.getText());
            price = Double.parseDouble(priceTF.getText());
            if (inHouse.isSelected()) {
                machineId = Integer.parseInt(origin);
            }
        } catch (NumberFormatException e) {
            // Values must be a number
            errorMessage += "foo must be a number \n";
        }

        if (name.length() == 0) {
            // Field empty
            errorMessage += "Name is empty \n";

        } else if (origin.length() == 0) {
            // Field empty
            errorMessage += "CompanyName is empty \n";
        }

        // Exit function if invalid
        if (errorMessage.length() > 0) {
            //TODO display message
            return;
        }

        // In-house or Outsourced?
        if (inHouse.isSelected()) {
            new InhousePart(name, price, inventory, min, max, machineId);
        } else {

        }
        exitButton.fire();
    }

    public void toMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/main.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void onInHouse(ActionEvent actionEvent) {
        sourceLabel.setText("Machine ID");
    }

    public void onOutsourced(ActionEvent actionEvent) {
        sourceLabel.setText("Company Name");
    }
}
