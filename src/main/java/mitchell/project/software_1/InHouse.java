package mitchell.project.software_1;

/**This is the class file for the InHouse Class, a subclass of Part
 * @author Mitchell Lantz
 */
public class InHouse extends Part {
    private int machineId;
    /**
     * This is the constructor for the {@link InHouse} subclass of {@link Part}, all fields are from the {@link Part} class except for the
     * companyName field, which belongs to the Outsourced subclass.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }


    /**
     * This is the getter method to Return the Private Integer Machine ID for the {@link InHouse} Object.
     * @return It will return the machineId for the {@link InHouse} object.
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * This is the setter method to set the Machine Id Private Integer value in the {@link InHouse} Object
     * @param machineId Machine ID Integer value to be added/modified to the {@link InHouse} object.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
