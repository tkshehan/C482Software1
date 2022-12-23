package tkshehan.c482software1.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int nextPartID = 1;
    private static int nextProductId = 1001;

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct (Product product) {
        allProducts.add(product);
    }

    public static Part lookupPart(int partId) {
        for(Part part : allParts) {
            if(part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String name) {
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        for(Part part : allParts) {
            if(part.getName().equals(name)) {
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }

    public static Product lookupProduct(int productId) {
        for(Product product : allProducts) {
            if(product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();
        for(Product product : allProducts) {
            if(product.getName().equals(name)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part part) {
        return allParts.remove(part);
    }

    public static boolean deleteProduct(Product product) {
        return allProducts.remove(product);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static int getNextPartID() {
        return nextPartID++;
    }

    public static int getNextProductId() {
        return nextProductId++;
    }

}
