package tkshehan.c482software1.model;

public class OutsourcedPart extends Part{
    private String companyName = "";

    public OutsourcedPart(int id, String name, double cost, int inventory, int min, int max, String companyName) {
        super(id, name, cost, inventory, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
