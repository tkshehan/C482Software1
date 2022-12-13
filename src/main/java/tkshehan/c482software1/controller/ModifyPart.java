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
import tkshehan.c482software1.model.Part;

import java.io.IOException;

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
    public void savePart(ActionEvent actionEvent) {
        // Validate Part
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
        // Save Part and Return to Main
        part.setName(name);
        part.setCost(price);
        part.setMin(min);
        part.setMax(max);
        part.setInventory(inventory);
        if (part instanceof OutsourcedPart) {
            ((OutsourcedPart) part).setCompanyName(origin);
        } else if (part instanceof  InhousePart) {
            ((InhousePart) part).setMachineId(machineId);
        }
        exitButton.fire();
    }
    public void setPart (Part part) {
        this.part = part;

        idTF.setText(String.valueOf(part.getId()));
        nameTF.setText(part.getName());
        invTF.setText(String.valueOf(part.getInventory()));
        priceTF.setText(String.valueOf(part.getCost()));
        minTF.setText(String.valueOf(part.getMin()));
        maxTF.setText(String.valueOf(part.getMax()));

        if(part instanceof OutsourcedPart) {
            outsourced.setSelected(true);
            sourceLabel.setText("Company Name");
            originTF.setText(((OutsourcedPart) part).getCompanyName());
        } else if (part instanceof  InhousePart) {
            originTF.setText(String.valueOf(((InhousePart) part).getMachineId()));
        }

    }
    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tkshehan/c482software1/main.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 480);
        stage.setScene(scene);
        stage.show();
    }
}
