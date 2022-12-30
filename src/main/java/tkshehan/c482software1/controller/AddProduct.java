package tkshehan.c482software1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tkshehan.c482software1.model.Inventory;
import tkshehan.c482software1.model.Part;
import tkshehan.c482software1.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class controls the add_product view.
 */
public class AddProduct implements Initializable {
    public TableView allPartsTable;
    public TableColumn allPartsIdCol;
    public TableColumn allPartsNameCol;
    public TableColumn allPartsInvCol;
    public TableColumn allPartsCostCol;
    public TableView asPartsTable;
    public TableColumn asPartsIdCol;
    public TableColumn asPartsNameCol;
    public TableColumn asPartsInvCol;
    public TableColumn asPartsCostCol;
    public TextField nameTF;
    public TextField invTF;
    public TextField priceTF;
    public TextField minTF;
    public TextField maxTF;
    public Button exitButton;
    public Text errorText;
    public TextField partsQuery;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    /**
     * This method adds a selected part to the associated parts.
     * @param actionEvent An action taken by the user.
     */
    public void addAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) allPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) return;
        associatedPartsList.add(selectedPart);
    }


    /**
     * This method removes a selected part from the associated parts.
     * @param actionEvent An aciton taken by the user.
     */
    public void removeAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) asPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) return;
        associatedPartsList.remove(selectedPart);
    }

    /**
     * This method validates the fields, saves the new product, then returns to the main view if successful.
     * @param actionEvent An action taken by the user.
     */
    public void saveProduct(ActionEvent actionEvent) {
        String errorMessage = "";
        String name = nameTF.getText().trim();
        int stock = 0;
        double price = 0;
        int min = 0;
        int max = 0;

        if (name.length() == 0) {
            // Field empty
            errorMessage += "Name must not be empty\n";
        }

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
        } else if (stock < min || stock > min) {
            errorMessage += "Inventory must be between Min and Max\n";
        }

        try {
            price = Double.parseDouble(priceTF.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Price must be a double\n";
        }

        // Exit function if invalid
        if (errorMessage.length() > 0) {
            errorText.setText(errorMessage);
            return;
        }

        Product product = new Product(Inventory.getNextProductId(), name, stock, price, min, max);
        product.setAllAssociatedParts(associatedPartsList);
        Inventory.addProduct(product);

        exitButton.fire();
    }

    /**
     * This method returns the application to the main view.
     * @param actionEvent An action taken by the user.
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
     * This method associates the table columns with the appropriate data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPartsTable.setItems(Inventory.getAllParts());
        allPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        asPartsTable.setItems(associatedPartsList);
        asPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        asPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        asPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        asPartsCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * This method searches for parts by the string input in partsQuery.
     * @param keyEvent A keypress in partsQuery
     */
    public void searchParts(KeyEvent keyEvent) {
        String query = partsQuery.getText().toLowerCase();
        ObservableList<Part> searchList = Inventory.lookupPart(query);
        if (searchList.size() == 0) {
            try {
                int queryId = Integer.parseInt(query);
                Part p = Inventory.lookupPart(queryId);
                if (p != null) {
                    searchList.add(p);
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        allPartsTable.setItems(searchList);
    }

}
