package tkshehan.c482software1.model;

public class Part {
    private int id;
    private String name = "";
    private double cost = 00.00;
    private int inventory = 0;
    private int max = 1;
    private int min = 0;

    public void Part(int id, String name, double cost, int inventory, int min, int max) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.inventory = inventory;
        this.min = min;
        this.max = max;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

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
