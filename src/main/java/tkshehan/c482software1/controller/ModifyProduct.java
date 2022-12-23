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

public class ModifyProduct implements Initializable {
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
    public Button exitButton;
    public Text errorText;
    public TextField maxTF;
    public TextField minTF;
    public TextField priceTF;
    public TextField invTF;
    public TextField nameTF;
    public TextField idTF;
    public TextField partsQuery;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();
    private Product product;

    public void addAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) allPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) return;
        associatedPartsList.add(selectedPart);
    }

    public void removeAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = (Part) asPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) return;
        associatedPartsList.remove(selectedPart);
    }

    public void saveProduct(ActionEvent actionEvent) {
        // Validate Product
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

        // Save Product
        product.setName(name);
        product.setStock(stock);
        product.setMin(min);
        product.setMax(max);
        product.setPrice(price);
        product.setAllAssociatedParts(associatedPartsList);

        exitButton.fire();
    }

    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tkshehan/c482software1/main.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void setProduct(Product product) {
        this.product = product;
        idTF.setText(String.valueOf(product.getId()));
        nameTF.setText(product.getName());
        invTF.setText(String.valueOf(product.getStock()));
        priceTF.setText(String.valueOf(product.getPrice()));
        minTF.setText(String.valueOf(product.getMin()));
        maxTF.setText(String.valueOf(product.getMax()));

        associatedPartsList.setAll(product.getAllAssociatedParts());
    }

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

    public void searchParts(KeyEvent keyEvent) {
        String query = partsQuery.getText().toLowerCase();
        ObservableList<Part> searchList = searchPartName(query);
        if (searchList.size() == 0) {
            try {
                int queryId = Integer.parseInt(query);
                Part p = searchPartId(queryId);
                if (p != null) {
                    searchList.add(p);
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        allPartsTable.setItems(searchList);
    }

    private ObservableList<Part> searchPartName(String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for (Part p : Inventory.getAllParts()) {
            if(p.getName().toLowerCase().contains(partialName)) {
                namedParts.add(p);
            }
        }
        return namedParts;
    }

    private Part searchPartId(int id) {
        for (Part p : Inventory.getAllParts()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}