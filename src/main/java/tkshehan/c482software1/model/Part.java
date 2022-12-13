package tkshehan.c482software1.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Part {
    private int id; //must be a unique number
    private String name = ""; //must not be empty
    private double cost = 00.00; //must be a double
    private int inventory = 0;  //must be >= 0
    private int max = 1; //must be greater than min
    private int min = 0; //must be >= 0

    public static ObservableList<Part> partsList = FXCollections.observableArrayList();

    public Part(String name, double cost, int inventory, int min, int max) {
        this.id = newId();
        this.name = name;
        this.cost = cost;
        this.inventory = inventory;
        this.min = min;
        this.max = max;

        partsList.add(this);
    }

    private int newId() {
        ArrayList idList = (ArrayList) partsList
                .stream()
                .map(Part::getId)
                .collect(Collectors.toList());
        for( int i = 1; ; i++) {
            if(!idList.contains(i))
            return i;
        }
    }

    public int getId() {return id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public double getCost() {return cost;}
    public void setCost(double cost) {this.cost = cost;}

    public int getInventory() {return inventory;}
    public void setInventory(int inventory) {this.inventory = inventory;}

    public int getMax() {return max;}
    public void setMax(int max) {this.max = max;}

    public int getMin() {return min;}
    public void setMin(int min) {this.min = min;}
}
