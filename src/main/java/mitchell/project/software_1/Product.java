package mitchell.project.software_1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**This is the class file for the Product Class
 * @author Mitchell Lantz
 */
public class Product {
        private int id;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;
        private ObservableList<Part>associatedParts  = FXCollections.observableArrayList();

        /**
         * Constructor method for the Product Object, which includes a field for and Observable List Array called
         * associatedParts that holds an array of {@link Part} objects that are needed to produce the product.
         * @param id
         * @param name
         * @param price
         * @param stock
         * @param min
         * @param max
         * @param associatedParts
         */
        public Product(int id, String name, double price, int stock, int min, int max,
                       ObservableList<Part> associatedParts) {
                this.id = id;
                this.name = name;
                this.price = price;
                this.stock = stock;
                this.min = min;
                this.max = max;
                this.associatedParts = associatedParts;
        }

        /**
         *This Method will add the associated part that is currently selected, it is used on the
         *  {@link ModifyPartController}
         *
         * @param product
         * @param part
         */
        public void addAssociatedPart(Product product, Part part){
                product.associatedParts.add(part);

        }

        /**
         * This method will return an Observable List Array of all {@link Part} Objects Asscociated with the given
         * {@link Product} Object
         * @param product This {@link Product} Object is selected currently on the {@link ModifyProductController}
         * @return Return value will be an Observable List Array of {@link Part} Objects
         */
        public ObservableList<Part> getAllAssociatedParts(Product product) {

                return product.associatedParts;
        }

        /**
         * This method will delete the associated {@link Part} object selected in the Associated Parts Table on the
         * {@link ModifyProductController}
         * @param product {@link Product} Object that is being modified
         * @param part {@link Part} Object that is selected in the Observable List of {@link Part} Objects from the
         *                         allParts List located in the {@link Inventory} Class.
         * @return true
         */
        public boolean deleteAssociatedPart(Product product, Part part){
                product.associatedParts.remove(part);
                return true;

        }

        /**
         * Getter for ID Private Integer
         * @return
         */
        public int getId() {
                return id;
        }

        /**
         * Setter for ID Private Integer
         * @param id
         */
        public void setId(int id) {
                this.id = id;
        }

        /**
         * Getter for Name Private String
         * @return
         */
        public String getName() {
                return name;
        }

        /**
         * Setter for Name Private String
         * @param name
         */
        public void setName(String name) {
                this.name = name;
        }

        /**
         * Getter for Price Private Double
         * @return
         */
        public double getPrice() {
                return price;
        }

        /**
         * Setter for Price Private Double
         * @param price
         */
        public void setPrice(double price) {
                this.price = price;
        }

        /**
         * Getter for Stock Private Integer
         * @return
         */
        public int getStock() {
                return stock;
        }

        /**
         * Setter for Stock Private Integer
         * @param stock
         */
        public void setStock(int stock) {
                this.stock = stock;
        }

        /**
         * Getter for Min Private Integer
         * @return
         */
        public int getMin() {
                return min;
        }

        /**
         * Setter for Min Private Integer
         * @param min
         */
        public void setMin(int min) {
                this.min = min;
        }

        /**
         * Getter for Max Private Integer
         * @return
         */
        public int getMax() {
                return max;
        }

        /**
         * Setter for Max Private Integer
         * @param max
         */
        public void setMax(int max) {
                this.max = max;
        }



}



