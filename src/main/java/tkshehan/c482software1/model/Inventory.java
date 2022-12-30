package tkshehan.c482software1.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This class contains the inventory of Parts and Products.
 * */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int nextPartID = 1;
    private static int nextProductId = 1001;

    /**
     * This method adds a part to the inventory.
     * @param part The part to add.
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * This method adds a product to the inventory.
     * @param product The product to add.
     */
    public static void addProduct (Product product) {
        allProducts.add(product);
    }

    /**
     * This method finds a part by its ID.
     * @param partId The IDf the part to look up.
     * @return part, or null if no part matches the ID.
     */
    public static Part lookupPart(int partId) {
        for(Part part : allParts) {
            if(part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * This method searches for parts with names containing the submitted string.
     * @param name The string to search for.
     * @return return an ObservableArrayList of the parts matching name.
     */
    public static ObservableList<Part> lookupPart(String name) {
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        for(Part part : allParts) {
            if(part.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }

    /**
     * This method finds a product by its ID.
     * @param productId The ID to search for.
     * @return product, or null if no product matches ID.
     */
    public static Product lookupProduct(int productId) {
        for(Product product : allProducts) {
            if(product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * This method searches for products with names containing the submitted string.
     * @param name The string to search.
     * @return an ObservableArrayList of products matching the name.
     */
    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();
        for(Product product : allProducts) {
            if(product.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    /**
     * This method replaces a part by index with the new selectedPart.
     * @param index The index of the part to update.
     * @param selectedPart The updated part.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    /**
     * This method replaces a product by index with the new product.
     * @param index The index of the product to update.
     * @param newProduct The updated product to set to the index.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * This method deletes a part from the inventory.
     * @param part The part to delete.
     * @return true on successful deletion or false if not.
     */
    public static boolean deletePart(Part part) {
        return allParts.remove(part);
    }

    /**
     * THis method deletes a product from the inventory.
     * @param product The product to delete.
     * @return true on successful deletion, or false if not.
     */
    public static boolean deleteProduct(Product product) {
        return allProducts.remove(product);
    }

    /**
     * This method returns all Parts.
     * @return all Parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This method returns all Products.
     * @return all Products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * This method returns and increments a unique part ID.
     * @return nextPartID.
     */
    public static int getNextPartID() {
        return nextPartID++;
    }

    /**
     * This method returns and increments a unique Product ID.
     * @return nextPartID.
     */
    public static int getNextProductId() {
        return nextProductId++;
    }

}
