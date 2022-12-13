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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tkshehan.c482software1.model.InhousePart;
import tkshehan.c482software1.model.OutsourcedPart;

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
    public Text errorText;

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
        } catch (NumberFormatException e) {
            errorMessage += "Inventory must be a number\n";
        }

        try {
            min = Integer.parseInt(minTF.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Min must be a number\n";
        }
        try {
            max = Integer.parseInt(maxTF.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Max must be a number\n";
        }
        if (max < min) {
            errorMessage += "Max must be greater or equal to Min\n";
        }

        try {
            price = Double.parseDouble(priceTF.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Price must be a double\n";
        }

        if (inHouse.isSelected()) {
            try {
                machineId = Integer.parseInt(origin);
            } catch (NumberFormatException e) {
                errorMessage += "MachineID must be a number\n";
            }
        }

        if (name.length() == 0) {
            // Field empty
            errorMessage += "Name must not be empty\n";

        }
        if (outsourced.isSelected() && origin.length() == 0) {
            // Field empty
            errorMessage += "CompanyName must not be empty\n";
        }

        // Exit function if invalid
        if (errorMessage.length() > 0) {
            errorText.setText(errorMessage);
            return;
        }

        // In-house or Outsourced?
        if (inHouse.isSelected()) {
            new InhousePart(name, price, inventory, min, max, machineId);
        } else {
            new OutsourcedPart(name, price, inventory, min, max, origin);
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
