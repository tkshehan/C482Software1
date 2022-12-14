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
import tkshehan.c482software1.model.InHouse;
import tkshehan.c482software1.model.Inventory;
import tkshehan.c482software1.model.Outsourced;

import java.io.IOException;

/**
 * This class controllers the add_part view.
 */
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

    /**
     * This method validates the fields, saves the new part, then returns to the main view if successful.
     * @param actionEvent An action taken by the user.
     */
    public void savePart(ActionEvent actionEvent) {
        String errorMessage = "";
        String name = nameTF.getText().trim();
        int stock = 0;
        double price  = 0;
        int min  = 0;
        int max = 0;
        String origin = originTF.getText().trim();
        int machineId = 0;

        try {
            stock = Integer.parseInt(invTF.getText());
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
        } else if (stock < min || stock > max) {
            errorMessage += "Inventory must be between Min and Max\n";
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
            Inventory.addPart(new InHouse(Inventory.getNextPartID(), name, price, stock, min, max, machineId));
        } else {
            Inventory.addPart(new Outsourced(Inventory.getNextPartID(), name, price, stock, min, max, origin));
        }
        exitButton.fire();
    }

    /**
     * This method returns the application to the main view.
     * @param actionEvent An action taken by the user.
     * @throws IOException
     */
    public void toMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/main.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method sets the origin label to "Machine ID".
     * @param actionEvent An action taken by the user.
     */
    public void onInHouse(ActionEvent actionEvent) {
        sourceLabel.setText("Machine ID");
    }

    /**
     * This method sets the origin label to "Company Name".
     * @param actionEvent An action taken by the user.
     */
    public void onOutsourced(ActionEvent actionEvent) {
        sourceLabel.setText("Company Name");
    }
}
