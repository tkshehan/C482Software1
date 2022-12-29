package tkshehan.c482software1.model;

/**
 * This class models parts made outside the company.
 */
public class Outsourced extends Part{
    private String companyName = "";

    public Outsourced(int id, String name, double cost, int stock, int min, int max, String companyName) {
        super(id, name, cost, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * This method returns the company name that supplies the part.
     * @return the company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method sets the company name that supplies the part.
     * @param companyName The company name to set.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
