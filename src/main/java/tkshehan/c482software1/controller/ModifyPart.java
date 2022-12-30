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
import tkshehan.c482software1.model.Part;

import java.io.IOException;

/**
 * This class controls the modify_part view.
 */
public class ModifyPart {
    public RadioButton inHouse;
    public RadioButton outsourced;
    public TextField nameTF;
    public TextField invTF;
    public TextField priceTF;
    public TextField minTF;
    public TextField maxTF;
    public Label sourceLabel;
    public TextField originTF;
    public Text errorText;
    public Button exitButton;
    public TextField idTF;
    private Part part;

    /**
     * This method validates the fields, saves the part, then returns to the main view if successful.
     * @param actionEvent An action from the user.
     */
    public void savePart(ActionEvent actionEvent) {
        // Validate Part
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

        // Save Part and Return to Main
        Part newPart;
        if (inHouse.isSelected()) {
            newPart = new InHouse(part.getId(), name, price, stock, min, max, machineId);
        } else {
            newPart = new Outsourced(part.getId(), name, price, stock, min, max, origin);
        }
        for(int i = 0; i < Inventory.getAllParts().size(); i++) {
            if(part == Inventory.getAllParts().get(i)) {
                Inventory.updatePart(i, newPart);
            }
        }
        exitButton.fire();
    }

    /**
     * This method sets the part to modify, and fills in the text fields with the data.
     * @param part The part to set.
     */
    public void setPart (Part part) {
        this.part = part;

        idTF.setText(String.valueOf(part.getId()));
        nameTF.setText(part.getName());
        invTF.setText(String.valueOf(part.getStock()));
        priceTF.setText(String.valueOf(part.getPrice()));
        minTF.setText(String.valueOf(part.getMin()));
        maxTF.setText(String.valueOf(part.getMax()));

        if(part instanceof Outsourced) {
            outsourced.setSelected(true);
            sourceLabel.setText("Company Name");
            originTF.setText(((Outsourced) part).getCompanyName());
        } else if (part instanceof InHouse) {
            originTF.setText(String.valueOf(((InHouse) part).getMachineId()));
        }

    }

    /**
     * This method returns the application to the main view.
     * @param actionEvent
     * @throws IOException
     */
    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tkshehan/c482software1/main.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method sets the origin label to "Machine ID".
     * @param actionEvent An action from the user.
     */
    public void onInHouse(ActionEvent actionEvent) {
        sourceLabel.setText("Machine ID");
        originTF.setText("");
    }

    /**
     * This method sets the origin label to "Company Name".
     * @param actionEvent An action from the user.
     */
    public void onOutsourced(ActionEvent actionEvent) {
        sourceLabel.setText("Company Name");
        originTF.setText("");
    }
}
