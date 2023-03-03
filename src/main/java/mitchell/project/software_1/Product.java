package mitchell.project.software_1;
/**This is the class file for the Product Class
 * @author Mitchell Lantz
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
        private int id;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;
        private ObservableList<Part>associatedParts  = FXCollections.observableArrayList();


        public Product(int id, String name, double price, int stock, int min, int max, ObservableList<Part> associatedParts) {
                this.id = id;
                this.name = name;
                this.price = price;
                this.stock = stock;
                this.min = min;
                this.max = max;
                this.associatedParts = associatedParts;
        }

        public void addAssociatedPart(Product product, Part part){
                product.associatedParts.add(part);

        }


        public ObservableList<Part> getAllAssociatedParts(Product product) {

                return product.associatedParts;
        }

        public boolean deleteAssociatedPart(Product product, Part part){
                product.associatedParts.remove(part);
                return true;

        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public int getStock() {
                return stock;
        }

        public void setStock(int stock) {
                this.stock = stock;
        }

        public int getMin() {
                return min;
        }

        public void setMin(int min) {
                this.min = min;
        }

        public int getMax() {
                return max;
        }

        public void setMax(int max) {
                this.max = max;
        }



}



