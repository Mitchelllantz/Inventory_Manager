package mitchell.project.software_1;
/**This is the Class file for the Outsourced class, a Subclass of Part
 * @author Mitchell Lantz
 */

public class Outsourced extends Part {
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;

    }


    public String getCompanyName() {
     return companyName;
    }

    public void setCompanyName(String companyName) {
     this.companyName = companyName;
    }
   }
