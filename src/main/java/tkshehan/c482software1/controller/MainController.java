package tkshehan.c482software1.controller;

import javafx.application.Platform;
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

    static private ObservableList<Part> partsList;
    public TableView partsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partCostCol;

    static private ObservableList<Product> productList;
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
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void setState(ObservableList<Part> parts, ObservableList<Product> products) {
        this.partsList = parts;
        this.productList = products;
        partsTable.setItems(partsList);
        productsTable.setItems(productList);
    }

    public void toAddPart(ActionEvent actionEvent) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/add_part.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void toModifyPart(ActionEvent actionEvent) throws IOException {
        Part selectedPart = (Part)partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) return;

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/modify_part.fxml"));
        Parent root = loader.load();

        ModifyPart controller = loader.getController();
        // Send Highlighted Part

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void toAddProduct(ActionEvent actionEvent) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/add_product.fxml"));
        Parent root = loader.load();

        AddProduct controller = loader.getController();
        controller.setPartsList(partsList);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void toModifyProduct(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = (Product)productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) return;

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/tkshehan/c482software1/modify_product.fxml"));
        Parent root = loader.load();

        ModifyProduct controller = loader.getController();
        controller.setPartsList(partsList);
        // Send Highlighted Product

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void modify(Product product) {
        int index = -1;
        for(int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == product.getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            productList.add(product);
        } else {
            productList.set(index, product);
        }
    }

    public void modify(Part part) {
        int index = -1;
        for(int i = 0; i < partsList.size(); i++) {
            if (partsList.get(i).getId() == part.getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            partsList.add(part);
        } else {
            partsList.set(index, part);
        }
    }

    public void deletePart(ActionEvent actionEvent) {
        Part selectedPart = (Part)partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) return;
        partsList.remove(selectedPart);
    }

    public void deleteProduct(ActionEvent actionEvent) {
        Product selectedProduct = (Product)productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) return;
        productList.remove(selectedProduct);
    }

    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }

}