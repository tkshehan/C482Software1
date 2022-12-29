package tkshehan.c482software1.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class models Products assembled from parts.
 */
public class Product {
    private int id;
    private String name = "";
    private int stock = 0;
    private double price = 00.00;
    private int min = 0;
    private int max = 1;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public Product(int id, String name, int stock, double price, int min, int max) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.min = min;
        this.max = max;
    }

    /**
     * This method returns the Product ID.
     * @return the ID.
     */
    public int getId() {return id;}

    /**
     * This method sets the Product ID.
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns the Product name.
     * @return the name.
     */
    public String getName() {return name;}

    /**
     * This method sets the Product name.
     * @param name The name to set.
     */
    public void setName(String name) {this.name = name;}

    /**
     * This method returns the Product stock.
     * @return the stock.
     */
    public int getStock() {return stock;}

    /**
     * This method sets the Product stock.
     * @param stock The stock to set.
     */
    public void setStock(int stock) {this.stock = stock;}

    /**
     * This method returns the Product price.
     * @return the price.
     */
    public double getPrice() {return price;}

    /**
     * This method sets the Product price.
     * @param price The price to set.
     */
    public void setPrice(double price) {this.price = price;}

    /**
     * THis method returns the minimum Product stock.
     * @return the min.
     */
    public int getMin() {return min;}

    /**
     * This method sets the minimum Product stock.
     * @param min the min to set.
     */
    public void setMin(int min) {this.min = min;}

    /**
     * This method returns the maximum Product stock.
     * @return the max.
     */
    public int getMax() {return max;}

    /**
     * This method sets the maximum Product stock.
     * @param max The max to set.
     */
    public void setMax(int max) {this.max = max;}

    /**
     * This method adds a part to the Products associated parts.
     * @param part The part to add.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This method removes a part from the Products associated parts.
     * @param part The part to remove.
     * @return true if deletion is successful, or false if not.
     */
    public boolean deleteAssociatedPart(Part part) {
        return associatedParts.remove(part);
    }

    /**
     * This method returns all associated parts.
     * @return the associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * This method sets all associated parts.
     * @param parts the parts to set.
     */
    public void setAllAssociatedParts(ObservableList<Part> parts) {
        associatedParts = parts;
    }
}


