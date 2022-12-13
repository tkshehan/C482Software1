package tkshehan.c482software1.model;

public class InhousePart extends Part {
    private int machineId;

    public InhousePart(String name, double cost, int inventory, int min, int max, int machineId) {
        super(name, cost, inventory, min, max);
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
