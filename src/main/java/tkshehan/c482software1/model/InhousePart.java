package tkshehan.c482software1.model;

public class InhousePart extends Part {
    private int machineId;

    private void InhousePart(int id, String name, double cost, int inventory, int min, int max, int machineId) {
        this.machineId = machineId;
        super.Part(id, name, cost, inventory, min, max);
    }

    public int getMachineId() {
        return machineId;
    }
}
