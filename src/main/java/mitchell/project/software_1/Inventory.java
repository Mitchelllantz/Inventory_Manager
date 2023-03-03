package mitchell.project.software_1;
/**This is the class file for the Inventory Class for the inventory app
 * @author Mitchell Lantz
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();



    public static void addPart(Part newPart) {
        allParts.add(newPart);

    }
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }



    public static Part searchByPartID(int id) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part p : allParts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    public static Product searchByProductID(int id) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product p : allProducts) {
            if (p.getId() == id) {
                return p;
            }

        }
        return null;
    }
    public static ObservableList<Part> searchByPartName(String subString) {
        ObservableList<Part> foundPart = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part part : allParts) {
            if (part.getName().contains(subString)) {
                foundPart.add(part);
            }


        }
        return foundPart;
    }
    public static ObservableList<Product> searchByProductName(String subString) {
        ObservableList<Product> foundProduct = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (Product p : allProducts) {
            if (p.getName().contains(subString)) {
                foundProduct.add(p);
            }

        }
        return foundProduct;
    }

    public static void updatePart(Part part, int id, String name, double price, int stock, int min, int max){
        part.setId(id);
        part.setName(name);
        part.setPrice(price);
        part.setStock(stock);
        part.setMin(min);
        part.setMax(max);

    }
    public static void updateProduct(Product product, int id, String name,
                                     double price, int stock, int min, int max){
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setMin(min);
        product.setMax(max);

    }
    public static boolean deletePart(Part part){
        allParts.remove(part);
        return true;
    }
    public static boolean deleteProduct(Product product){
        allProducts.remove(product);
        return true;
    }
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
   public static void addTestData() {
     allParts.add(new InHouse(10000, "Muffler", 250, 40, 1, 100, 34405));
     allParts.add(new InHouse(10010, "Brakes", 125, 75, 1, 100, 34345));
     allParts.add(new InHouse(10020, "Wheels", 1200, 90, 1, 100, 33535));

     ObservableList<Part> parts1 = FXCollections.observableArrayList();;


     allProducts.add(new Product(60000, "SUV", 40000.00, 40, 1, 100, parts1));
     allProducts.add(new Product( 61000,"Sedan", 30000.00, 75, 1, 100, parts1 ));
     allProducts.add(new Product( 62000,"Coupe", 20000.00, 90, 1, 100, parts1 ));




    }



}