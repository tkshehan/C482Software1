package tkshehan.c482software1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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

public class ModifyProduct {
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

    private ObservableList<Part> partsList;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();
    private Product product;
    public void addAssociatedPart(ActionEvent actionEvent) {
    }

    public void removeAssociatedPart(ActionEvent actionEvent) {
    }

    public void saveProduct(ActionEvent actionEvent) {
        // Validate Product

        // Save Product
    }

    public void toMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tkshehan/c482software1/main.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void setPartsList(ObservableList<Part> partsList) {
        this.partsList = partsList;
        allPartsTable.setItems(partsList);
        allPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        allPartsCostCol.setCellValueFactory(new PropertyValueFactory<>("cost"));


        asPartsTable.setItems(associatedPartsList);
        asPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        asPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        asPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        asPartsCostCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}