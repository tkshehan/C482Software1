package tkshehan.c482software1.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getStock() {return stock;}
    public void setStock(int inventory) {this.stock = stock;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public int getMin() {return min;}
    public void setMin(int min) {this.min = min;}

    public int getMax() {return max;}
    public void setMax(int max) {this.max = max;}

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    public boolean deleteAssociatedPart(Part part) {
        return associatedParts.remove(part);
    }
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public void setAllAssociatedParts(ObservableList<Part> parts) {
        associatedParts = parts;
    }
}


