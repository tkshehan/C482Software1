package tkshehan.c482software1.model;

public class OutsourcedPart extends Part{
    private String companyName = "";

    private void OutsourcedPart(int id, String name, double cost, int inventory, int min, int max, String companyName) {
        this.companyName = companyName;
        super.Part(id, name, cost, inventory, min, max);
    }

    public String getCompanyName() {
        return companyName;
    }
}
