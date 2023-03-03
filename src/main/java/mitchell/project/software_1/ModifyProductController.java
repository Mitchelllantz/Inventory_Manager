package mitchell.project.software_1;
/**This is class file for the Controller for the modify product page of the inventory application.
 * @author Mitchell Lantz
 */

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
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {
    public TextField modProdIDField;
    public TextField modProdNameField;
    public TextField modProdInvField;
    public TextField modProdPriceField;
    public TextField modProdMaxField;
    public TextField modProdMinField;
    public TableView<Part> modProductPartsTable;
    public TableColumn<Object, Object> modProductPartsTablePartID;
    public TableColumn<Object, Object> modProductPartsTablePartName;
    public TableColumn<Object, Object> modProductPartsTableInvLevel;
    public TableColumn<Object, Object> modProductPartsTablePrice;
    public TableView<Part> modProductAssociatedPartsTable;
    public TableColumn<Object, Object> associatedPartsID;
    public TableColumn<Object, Object> associatedPartsName;
    public TableColumn<Object, Object> associatedPartsStock;
    public TableColumn<Object, Object> associatedPartsPrice;
    public Button modProdAddPartButton;
    public Button removeAssociatedPartButton;
    public Button modProdSaveButton;
    public Button modProdCancelButton;
    public TextField modProductPartSearch;
    private static Product productData;

    /**
     *
     * @param product The selected product from the main page
     */
    public static void moveData(Product product){
        productData = product;
    }

    /**
     * This method initializes the tables needed for the page, as well as displays data in each of the text fields, data
     * given in the static method moveData. The modProductsPartsTable is set with the allParts static Observable List
     * from the Inventory class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modProductPartsTable.setItems(Inventory.getAllParts());
        modProductPartsTablePartID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        modProductPartsTablePartName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        modProductPartsTablePrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        modProductPartsTableInvLevel.setCellValueFactory(new PropertyValueFactory<>("Stock"));

        modProductAssociatedPartsTable.setItems(productData.getAllAssociatedParts(productData));
        associatedPartsID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        associatedPartsStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        associatedPartsName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        associatedPartsPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        modProdIDField.setText(String.valueOf(productData.getId()));
        modProdNameField.setText(String.valueOf(productData.getName()));
        modProdPriceField.setText(String.valueOf(productData.getPrice()));
        modProdMinField.setText(String.valueOf(productData.getMin()));
        modProdMaxField.setText(String.valueOf(productData.getMax()));
        modProdInvField.setText(String.valueOf(productData.getStock()));


    }

    /**
     *
     * @param actionEvent
     * 
     */
    public void onAddB(ActionEvent actionEvent) {

        Part selectedPart= (Part) modProductPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert noSelection = new Alert(Alert.AlertType.CONFIRMATION);
            noSelection.initModality(Modality.NONE);
            noSelection.setHeaderText("Please Select a Part");
            noSelection.setContentText("Please Select a Part");
            noSelection.setTitle("Error");
            Optional<ButtonType> confirm = noSelection.showAndWait();
        }
        else {
            productData.addAssociatedPart(productData, selectedPart);
        }


    }

    public void onRemoveAssociatedPartButton (ActionEvent actionEvent){
        Part selectedPart= (Part) modProductAssociatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert noSelection = new Alert(Alert.AlertType.CONFIRMATION);
            noSelection.initModality(Modality.NONE);
            noSelection.setHeaderText("Please Select a Part");
            noSelection.setContentText("Please Select a Part");
            noSelection.setTitle("Error");
            Optional<ButtonType> confirm = noSelection.showAndWait();
        }
        else {
            productData.deleteAssociatedPart(productData, selectedPart);
        }


    }


    public void onSaveButton(ActionEvent actionEvent) throws IOException {
        String productIdS = modProdIDField.getText();
        String productNameS = modProdNameField.getText();
        String productPriceS = modProdPriceField.getText();
        String productStockS = modProdInvField.getText();
        String productMinS = modProdMinField.getText();
        String prodMaxS = modProdMaxField.getText();


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
        int id;
        try {
            id = Integer.parseInt(productIdS);
        }
        catch(NumberFormatException e) {
            Alert minWrongType = new Alert(Alert.AlertType.CONFIRMATION);
            minWrongType.initModality(Modality.NONE);
            minWrongType.setHeaderText("ID Number must be a Number");
            minWrongType.setTitle("ID Number must be a Number");
            minWrongType.setTitle("Error");
            Optional<ButtonType> confirm = minWrongType.showAndWait();
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
        if(min <= max && stock <= max && stock >= min) {
            Inventory.updateProduct(productData, id, productNameS, price, stock, min, max );
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
        }

    }



    @FXML
    private void back(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm that you want to Cancel");
        alert.setContentText("Please confirm that you want to cancel");
        alert.setHeaderText("Confirm");
        Optional<ButtonType> confirm = alert.showAndWait();

        if (confirm.get() == ButtonType.OK) {
            Parent partAddScreenCancel = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
            Scene scene = new Scene(partAddScreenCancel);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
        }

        

    }


    public void searchPart(ActionEvent actionEvent) {
        String q = modProductPartSearch.getText();
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

        modProductPartsTable.setItems(productQuery);


    }
}