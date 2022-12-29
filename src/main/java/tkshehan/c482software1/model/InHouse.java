package tkshehan.c482software1.model;

/**
 * This class models InHouse made Parts.
 */
public class InHouse extends Part {
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * This method returns the Part Machine ID.
     * @return the machineID.
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * This method sets the Machine ID.
     * @param machineId The ID to set.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
