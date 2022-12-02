package tkshehan.c482software1.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tkshehan.c482software1.model.Part;
import tkshehan.c482software1.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private ObservableList<Part> partsList = FXCollections.observableArrayList();
    public TableView partsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partCostCol;

    private ObservableList<Product> productList = FXCollections.observableArrayList();
    public TableView productsTable;
    public TableColumn productIdCol;
    public TableColumn productNameCol;
    public TableColumn productInvCol;
    public TableColumn productPriceCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    partsTable.setItems(partsList);
    partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    partInvCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
    partCostCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

    productsTable.setItems(productList);
    productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    productInvCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
    productPriceCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }

    public void toAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tkshehan/c482software1/add_part.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();

    }

    public void toModifyPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tkshehan/c482software1/add_product.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void toAddProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tkshehan/c482software1/add_product.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void toModifyProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tkshehan/c482software1/modify_product.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void deletePart(ActionEvent actionEvent) {
    }

    public void deleteProduct(ActionEvent actionEvent) {
    }

    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }


}