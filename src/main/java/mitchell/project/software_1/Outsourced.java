package mitchell.project.software_1;


/**This is the Class file for the Outsourced class, a Subclass of Part
 * @author Mitchell Lantz
 */
public class Outsourced extends Part {
    private String companyName;

    /**
     * This is the constructor for the {@link Outsourced} subclass of ={@link Part}, all fields are from the {@link Part} class except for the
     * Comapny Name field, which belongs to the Outsourced subclass.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;

    }

    /**
     * This is the getter method to return the Company Name Private String
     * @return it will return the string of Company Name for the {@link Outsourced} Object.
     */
    public String getCompanyName() {
     return companyName;
    }

    /**
     * This is the setter method to set the Company Name Private String for the {@link Outsourced} object.
     * @param companyName String of the company name to be added/modified to the {@link Outsourced} Object.
     */

    public void setCompanyName(String companyName) {
     this.companyName = companyName;
    }
   }
