package tkshehan.c482software1.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tkshehan.c482software1.model.Inventory;
import tkshehan.c482software1.model.Part;
import tkshehan.c482software1.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controls the main view of the application.
 */
public class MainController implements Initializable {

    public TextField partsQuery;
    public TableView partsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partCostCol;
    
    public TextField productQuery;
    public TableView productsTable;
    public TableColumn productIdCol;
    public TableColumn productNameCol;
    public TableColumn productInvCol;
    public TableColumn productPriceCol;


    /**
     * This method associates the table columns with the appropriate data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * This method moves the application to the add_part view.
     * @param actionEvent An action from the user.
     * @throws IOException
     */
    public void toAddPart(ActionEvent actionEvent) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/add_part.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method moves the application to the modify_part view, using the selected part. <br />
     * <br />
     * RUNTIME ERROR: Cannot invoke "tkshehan.c482software1.model.Part.getId()" because "part" is null. <br />
     * Fixed by checking for null before moving to modify part screen.
     * @param actionEvent An action from the user.
     * @throws IOException
     */
    public void toModifyPart(ActionEvent actionEvent) throws IOException {
        Part selectedPart = (Part)partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) return;

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/modify_part.fxml"));
        Parent root = loader.load();

        ModifyPart controller = loader.getController();
        controller.setPart(selectedPart);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method moves the application to the add_product view.
     * @param actionEvent An action from the user.
     * @throws IOException
     */
    public void toAddProduct(ActionEvent actionEvent) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/add_product.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method moves the application to the modify_product view, using the selected product.
     * @param actionEvent An action from the user.
     * @throws IOException
     */
    public void toModifyProduct(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = (Product)productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) return;

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/modify_product.fxml"));
        Parent root = loader.load();

        ModifyProduct controller = loader.getController();
        controller.setProduct(selectedProduct);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method deletes a selected part from the inventory, after confirmation.
     * The part must not be used in any products.
     * @param actionEvent An action from the user.
     */
    public void deletePart(ActionEvent actionEvent) {
        Part selectedPart = (Part)partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete " + selectedPart.getName() + ". \n Do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
        }
    }

    /**
     * This method deletes a product from the inventory, after confirmation.
     * @param actionEvent An action from the user.
     */
    public void deleteProduct(ActionEvent actionEvent) {
        Product selectedProduct = (Product)productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) return;

        if (selectedProduct.getAllAssociatedParts().size() != 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error: Product In Use");
            alert.setContentText("Products currently associated with parts cannot be deleted");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete " + selectedProduct.getName() + ". \n Do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Inventory.deleteProduct(selectedProduct);
        }
    }

    /**
     * This method exits the application, after confirmation.
     * @param actionEvent An action from the user.
     */
    public void quit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }

    }


    /**
     * This method searches for parts by the string input in partsQuery.
     * @param keyEvent A keypress from the user.
     */
    public void partSearch(KeyEvent keyEvent) {
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
        partsTable.setItems(searchList);
    }

    /**
     * This method searches for products by the string input in productQuery.
     * @param keyEvent A keypress from the user.
     */
    public void productSearch(KeyEvent keyEvent) {
        String query = productQuery.getText().toLowerCase();
        ObservableList<Product> searchList = Inventory.lookupProduct(query);
        if (searchList.size() == 0) {
            try {
                int queryId = Integer.parseInt(query);
                Product p = Inventory.lookupProduct(queryId);
                if (p != null) {
                    searchList.add(p);
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        productsTable.setItems(searchList);
    }
}