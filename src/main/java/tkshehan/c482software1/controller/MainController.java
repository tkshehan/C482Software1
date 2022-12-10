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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tkshehan.c482software1.model.Part;
import tkshehan.c482software1.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Part.partsList);
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

        productsTable.setItems(Product.productList);
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void updateState() {
        partsTable.setItems(Part.partsList);
        productsTable.setItems(Product.productList);
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
        controller.setPartsList(Part.partsList);

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
        controller.setPartsList(Part.partsList);
        // Send Highlighted Product

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void modify(Product product) {
        int index = -1;
        for(int i = 0; i < Product.productList.size(); i++) {
            if (Product.productList.get(i).getId() == product.getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Product.productList.add(product);
        } else {
            Product.productList.set(index, product);
        }
    }

    public void modify(Part part) {
        int index = -1;
        for(int i = 0; i < Part.partsList.size(); i++) {
            if (Part.partsList.get(i).getId() == part.getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Part.partsList.add(part);
        } else {
            Part.partsList.set(index, part);
        }
    }

    public void deletePart(ActionEvent actionEvent) {
        Part selectedPart = (Part)partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) return;
        Part.partsList.remove(selectedPart);
    }

    public void deleteProduct(ActionEvent actionEvent) {
        Product selectedProduct = (Product)productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) return;
        Product.productList.remove(selectedProduct);
    }

    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }


    public void partSearch(KeyEvent keyEvent) {
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

        partsTable.setItems(searchList);
    }

    private ObservableList<Part> searchPartName(String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for (Part p : Part.partsList) {
            if(p.getName().toLowerCase().contains(partialName)) {
                namedParts.add(p);
            }
        }
        return namedParts;
    }

    private Part searchPartId(int id) {
        for (Part p : Part.partsList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void productSearch(KeyEvent keyEvent) {
        String query = productQuery.getText().toLowerCase();
        ObservableList<Product> searchList = searchProductName(query);
        if (searchList.size() == 0) {
            try {
                int queryId = Integer.parseInt(query);
                Product p = searchProductId(queryId);
                if (p != null) {
                    searchList.add(p);
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }

        productsTable.setItems(searchList);
    }

    private  ObservableList<Product> searchProductName(String partialName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        for (Product p : Product.productList) {
            if(p.getName().toLowerCase().contains(partialName)) {
                namedProducts.add(p);
            }
        }
        return  namedProducts;
    }

    private Product searchProductId(int id) {
        for (Product p : Product.productList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

}