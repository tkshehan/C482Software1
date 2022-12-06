package tkshehan.c482software1.model;

import java.util.ArrayList;

public class Product {
    private int id;
    private String name = "";
    private int inventory = 0;
    private double price = 00.00;
    private int min = 0;
    private int max = 1;
    private ArrayList<Part> partsList = new ArrayList<>();

    public Product(int id, String name, int inventory, double price, int min, int max, ArrayList<Part> partsList) {
    this.id = id;
    this.name = name;
    this.inventory = inventory;
    this.price = price;
    this.min = min;
    this.max = max;
    this.partsList = partsList;
    }

    public int getId() {return id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getInventory() {return inventory;}
    public void setInventory(int inventory) {this.inventory = inventory;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public int getMin() {return min;}
    public void setMin(int min) {this.min = min;}

    public int getMax() {return max;}
    public void setMax(int max) {this.max = max;}
}
