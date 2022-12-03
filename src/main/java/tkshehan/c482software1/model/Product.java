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

}
