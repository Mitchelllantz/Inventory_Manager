package mitchell.project.software_1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**This is the class file for the Inventory Class for the inventory application.
 * <p> <b>FUTURE ENHANCEMENT</b> Deleting parts without checking if they are associated with a product seems
 * like it will cause errors down the road for users. A new feature could be added that would allow you to see what
 * Products have the Part associated with it, and allow you to modify that selection before removing the Part from the
 * program. I would have it open the modify parts page and force you to modify/delete the Parts that in question before
 * allowing you to delete the part in the main inventory screen.</p>
 * @author Mitchell Lantz
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /**
     *
     * @param newPart The newPart is of abstract class {@link Part} and is passed through as a parameter to be added to the {@link Inventory#allParts} Observable list array.
     *                This method is used by {@link AddPartController} primarily.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);

    }

    /**
     *
     * @param newProduct The newProduct is of the {@link Product} Class and is passed through as a parameter to be added to the {@link Inventory#allProducts} Observable list array.
     *                   This method is used by {@link AddProductController} primarily.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }


    /**
     *
     * @param id The input is the number input into the search field on displays of {@link Inventory#allParts} Observable list arrays.
     * @return This will return the Part associated with the ID if it is a direct match only.
     */
    public static Part searchByPartID(int id) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part p : allParts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param id The input is the number input into the search field on displays of {@link Inventory#allProducts} Observable list arrays.
     * @return This will return the Product associated with the ID if it is a direct match only.
     */
    public static Product searchByProductID(int id) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product p : allProducts) {
            if (p.getId() == id) {
                return p;
            }

        }
        return null;
    }

    /**
     *
     * @param subString The input is a string from the search text field that will search for all cases of it appearing as a substring in the {@link Inventory#allParts} Observable list arrays.
     * @return This will return the Part or Parts associated with the substring that is input into the search bar.
     */
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

    /**
     *
     * @param subString The input is a string from the search text field that will search for all cases of it appearing as a substring in the {@link Inventory#allProducts} Observable list arrays.
     * @return This will return the Product or Products associated with the substring that is input into the search bar.
     */
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

    /**
     * The method with update an existing Part, used primarily by the {@link ModifyPartController}. The method receives all the parameters from the data validated text fields, a separate method will update the subclass fields of Part.
     * @param part The Part object needs to be specified to select an existing part to update.
     * @param id This value is given in the data validated text field on the {@link ModifyPartController}.
     * @param name his value is given in the data validated text field on the {@link ModifyPartController}.
     * @param price his value is given in the data validated text field on the {@link ModifyPartController}.
     * @param stock his value is given in the data validated text field on the {@link ModifyPartController}.
     * @param min his value is given in the data validated text field on the {@link ModifyPartController}.
     * @param max his value is given in the data validated text field on the {@link ModifyPartController}.
     */
    public static void updatePart(Part part, int id, String name, double price, int stock, int min, int max){
        part.setId(id);
        part.setName(name);
        part.setPrice(price);
        part.setStock(stock);
        part.setMin(min);
        part.setMax(max);

    }

    /**
     * The method with update an existing Product, used primarily by the {@link ModifyProductController}. The method receives all the parameters from the data validated text fields. The associated parts field of the Product object is handled separateness.
     * @param product The Product object needs to be specified to select an existing product to update.
     * @param id This value is given in the data validated text field on the {@link ModifyProductController}.
     * @param name This value is given in the data validated text field on the {@link ModifyProductController}.
     * @param price This value is given in the data validated text field on the {@link ModifyProductController}.
     * @param stock This value is given in the data validated text field on the {@link ModifyProductController}.
     * @param min This value is given in the data validated text field on the {@link ModifyProductController}.
     * @param max This value is given in the data validated text field on the {@link ModifyProductController}.
     */
    public static void updateProduct(Product product, int id, String name,
                                     double price, int stock, int min, int max){
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setMin(min);
        product.setMax(max);

    }
    /**
     *
     * @param part Specifies the Part to be deleted.
     * @return true.
     */
    public static boolean deletePart(Part part){
        allParts.remove(part);
        return true;
    }

    /**
     *
     * @param product Specifies the Product to be deleted.
     * @return true.
     */
    public static boolean deleteProduct(Product product){
        allProducts.remove(product);
        return true;
    }

    /**
     * Getter to return the {@link Inventory#allParts} array.
     * @return {@link Inventory#allParts}
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Getter to return the {@link Inventory#allProducts} array.
     * @return {@link Inventory#allProducts}.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Adds test data to the Arrays, called at {@link Main}.
     */
   public static void addTestData() {
     allParts.add(new InHouse(10000, "Muffler", 250, 40, 1, 100, 34405));
     allParts.add(new InHouse(10010, "Brakes", 125, 75, 1, 100, 34345));
     allParts.add(new InHouse(10020, "Wheels", 1200, 90, 1, 100, 33535));

     ObservableList<Part> parts1 = FXCollections.observableArrayList();
     ObservableList<Part> parts2 = FXCollections.observableArrayList();
     ObservableList<Part> parts3 = FXCollections.observableArrayList();


     allProducts.add(new Product(60000, "SUV", 40000.00, 40, 1, 100, parts1));
     allProducts.add(new Product( 61000,"Sedan", 30000.00, 75, 1, 100, parts2 ));
     allProducts.add(new Product( 62000,"Coupe", 20000.00, 90, 1, 100, parts3 ));




    }



}