package mitchell.project.software_1;
/**This is the class file for the controller of the add product page of the inventory app
 * @author Mitchell Lantz
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    public TextField addProdIDField;
    public TextField addProdNameField;
    public TextField addProdInvField;
    public TextField addProdPriceField;
    public TextField addProdMaxField;
    public TextField addProdMinField;
    public TableView<Part> addProductAsscPartsTable;
    public Button addProdAddPartButton;
    public Button removeAssociatedPartButton;
    public Button addProdSaveButton;
    public Button addProdCancelButton;
    public TextField addProductPartSearch;
    public TableView<Part> addProductPartsTable;
    public TableColumn<Object, Object> addProductPartsTablePartID;
    public TableColumn<Object, Object> addProductPartsTablePartName;
    public TableColumn<Object, Object> addProductPartsTableInvLevel;
    public TableColumn<Object, Object> addProductPartsTablePrice;
    public TableColumn<Object, Object> associatedPartsID;
    public TableColumn<Object, Object> associatedPartsStock;
    public TableColumn<Object, Object> associatedPartsName;
    public TableColumn<Object, Object> associatedPartsPrice;
    ObservableList<Part> associatedPartsEmpty = FXCollections.observableArrayList();
    public static int lastId = 62000;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        lastId += 1000;
        String text = Integer.toString(lastId);
        addProdIDField.setText(text);
        addProductPartsTable.setItems(Inventory.getAllParts());
        addProductPartsTablePartID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        addProductPartsTablePartName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        addProductPartsTablePrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        addProductPartsTableInvLevel.setCellValueFactory(new PropertyValueFactory<>("Stock"));


        addProductAsscPartsTable.setItems(associatedPartsEmpty);
        associatedPartsID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        associatedPartsStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        associatedPartsName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        associatedPartsPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

    }
    public void onRemoveAssociatedPartButton (ActionEvent actionEvent){
        Part selectedPart= (Part) addProductAsscPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert noSelection = new Alert(Alert.AlertType.CONFIRMATION);
            noSelection.initModality(Modality.NONE);
            noSelection.setHeaderText("Please Select a Part");
            noSelection.setContentText("Please Select a Part");
            noSelection.setTitle("Error");
            Optional<ButtonType> confirm = noSelection.showAndWait();
        }
        else {
            associatedPartsEmpty.remove(selectedPart);
        }




    }




    public void onAddB(ActionEvent actionEvent) {

        Part selectedPart= (Part) addProductPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert noSelection = new Alert(Alert.AlertType.CONFIRMATION);
            noSelection.initModality(Modality.NONE);
            noSelection.setHeaderText("Please Select a Part");
            noSelection.setContentText("Please Select a Part");
            noSelection.setTitle("Error");
            Optional<ButtonType> confirm = noSelection.showAndWait();
        }
        else {
            associatedPartsEmpty.add(selectedPart);
        }
    }



    public void searchPart(ActionEvent actionEvent) {
        String q = addProductPartSearch.getText();
        ObservableList<Part> productQuery = Inventory.searchByPartName(q);
        if (productQuery.size() < 1) {
            try {
                int iDNum = Integer.parseInt(q);
                Part lookupID = Inventory.searchByPartID(iDNum);
                if (lookupID != null)
                    productQuery.add(lookupID);
                else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initModality(Modality.NONE);
                    alert.setHeaderText("Part Not Found");
                    alert.setTitle("Part Not Found");
                    alert.setTitle("Error");
                    Optional<ButtonType> confirm = alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.NONE);
                alert.setHeaderText("Part Not Found");
                alert.setTitle("Part Not Found");
                alert.setTitle("Error");
                Optional<ButtonType> confirm = alert.showAndWait();

            }
        }

        addProductPartsTable.setItems(productQuery);


    }

   @FXML
   private void back(ActionEvent actionEvent) throws IOException {
    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.initModality(Modality.NONE);
    confirmationAlert.setTitle("Confirm that you want to Cancel");
    confirmationAlert.setContentText("Please confirm that you want to cancel");
    confirmationAlert.setHeaderText("Confirm");

    Optional<ButtonType> confirm = confirmationAlert.showAndWait();
            if(confirm.get() == ButtonType.OK){
                lastId -= 1000;
                Parent productAddScreenCancel = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("inventoryPage.fxml")));
                Scene scene = new Scene(productAddScreenCancel);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }

        }

    public void onSaveButton(ActionEvent actionEvent) throws IOException {
        String productNameS = addProdNameField.getText();
        String productPriceS = addProdPriceField.getText();
        String productStockS = addProdInvField.getText();
        String productMinS = addProdMinField.getText();
        String prodMaxS = addProdMaxField.getText();
        ObservableList associatedParts = associatedPartsEmpty;

        if(productNameS.isBlank()){
            Alert nameTFError = new Alert(Alert.AlertType.CONFIRMATION);
            nameTFError.initModality(Modality.NONE);
            nameTFError.setHeaderText("Product Name Field is Blank");
            nameTFError.setContentText("Product Name Field is Blank");
            nameTFError.setTitle("Error");
            Optional<ButtonType> confirm = nameTFError.showAndWait();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(productPriceS);
        }
        catch(NumberFormatException e){
            Alert priceWrongType = new Alert(Alert.AlertType.CONFIRMATION);
            priceWrongType.initModality(Modality.NONE);
            priceWrongType.setHeaderText("Price Input Must be a Number");
            priceWrongType.setContentText("Price Input Must be a Number");
            priceWrongType.setTitle("Error");
            Optional<ButtonType> confirm = priceWrongType.showAndWait();
            return;

        }

        int stock;
        try {
            stock = Integer.parseInt(productStockS);
        }
        catch(NumberFormatException e){
            Alert stockWrongType = new Alert(Alert.AlertType.CONFIRMATION);
            stockWrongType.initModality(Modality.NONE);
            stockWrongType.setHeaderText("Inventory Input Must be a Number");
            stockWrongType.setContentText("Inventory Input Must be a Number");
            stockWrongType.setTitle("Error");
            Optional<ButtonType> confirm = stockWrongType.showAndWait();
            return;
        }
        int min;
        try {
            min = Integer.parseInt(productMinS);
        }
        catch(NumberFormatException e) {
            Alert minWrongType = new Alert(Alert.AlertType.CONFIRMATION);
            minWrongType.initModality(Modality.NONE);
            minWrongType.setHeaderText("Minimum Number must be a Number");
            minWrongType.setTitle("Minimum Number must be a Number");
            minWrongType.setTitle("Error");
            Optional<ButtonType> confirm = minWrongType.showAndWait();
            return;
        }
        int max;
        try {
            max = Integer.parseInt(prodMaxS);
        }
        catch(NumberFormatException e){
            Alert maxWrongType = new Alert(Alert.AlertType.CONFIRMATION);
            maxWrongType.initModality(Modality.NONE);
            maxWrongType.setHeaderText("Max Number must be a Number");
            maxWrongType.setTitle("Max Number must be a Number");
            maxWrongType.setTitle("Error");
            Optional<ButtonType> confirm = maxWrongType.showAndWait();
            return;

        }
               if(min <= max && stock <= max && stock >= min && !associatedParts.isEmpty()) {
                Product product = new Product(lastId, productNameS, price, stock, min, max, associatedParts );
                Inventory.addProduct(product);
                Parent inventoryScreen = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
                Scene scene = new Scene(inventoryScreen);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }

                else if(max < min){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.NONE);
                alert.setHeaderText("Minimum Number must be less than Maximum");
                alert.setTitle("Minimum Number must be less than Maximum");
                alert.setTitle("Error");
                Optional<ButtonType> confirm = alert.showAndWait();
                return;
            } else if (min > max) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.NONE);
                alert.setHeaderText("Minimum Number must be less than Maximum");
                alert.setTitle("Minimum Number must be less than Maximum");
                alert.setTitle("Error");
                Optional<ButtonType> confirm = alert.showAndWait();
                return;

            } else if (stock > max) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.NONE);
                alert.setHeaderText("Inventory Amount is Over Maximum Amount");
                alert.setTitle("Inventory Amount is Over Maximum Amount");
                alert.setTitle("Error");
                Optional<ButtonType> confirm = alert.showAndWait();
                return;
            } else if (stock < min) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.NONE);
                alert.setHeaderText("Inventory Amount is Less than Minimum Amount");
                alert.setTitle("Inventory Amount is Less than Minimum Amount");
                alert.setTitle("Error");
                Optional<ButtonType> confirm = alert.showAndWait();
                return;
            } else if (associatedParts.isEmpty()){
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.initModality(Modality.NONE);
                   alert.setHeaderText("Must Contain Associated Parts");
                   alert.setTitle("Must Contain Associated Parts");
                   alert.setTitle("Error");
                   Optional<ButtonType> confirm = alert.showAndWait();
                   return;



    }

}
}
