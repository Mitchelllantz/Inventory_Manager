package mitchell.project.software_1;
/**This is the class file for the main controller for the inventory program
 * @author Mitchell Lantz
 */

import javafx.application.Platform;
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






public class Controller implements Initializable {


    @FXML
    public TableColumn<Object, Object> partID;
    @FXML
    public TableColumn<Object, Object> partName;
    @FXML
    public TableColumn<Object, Object> partInventoryLevel;
    @FXML
    public TableColumn<Object, Object> partPrice;
    @FXML
    public Button addPartButton;
    @FXML
    public Button modifyPartButton;
    @FXML
    public Button deletePartButton;
    @FXML
    public Button deleteProductButton;
    @FXML
    public Button modifyProductButton;
    @FXML
    public Button addProductButton;
    @FXML
    public TextField partSearchBar;
    @FXML
    public TableView<Part> mainList;
    @FXML
    public TableView<Product> prodMainView;
    @FXML
    public TableColumn<Object, Object> prodIDTable;
    @FXML
    public TableColumn<Object, Object> prodNameTable;
    @FXML
    public TableColumn<Object, Object> prodInvTable;
    @FXML
    public TableColumn<Object, Object> prodPriceTable;
    @FXML
    public TextField productSearchBar;
    @FXML
    public Button quitButton;
    @FXML
    public Button partsSearch;
    @FXML
    public Button productSearch;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainList.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        partInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("Stock"));

        prodMainView.setItems(Inventory.getAllProducts());
        prodIDTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvTable.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceTable.setCellValueFactory(new PropertyValueFactory<>("price"));


    }


    public void toAddPartPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addPartPage.fxml")));
        Scene addPartScene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add a Part");
        stage.setScene(addPartScene);
        stage.show();

    }

    public void toAddProductPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addProductPage.fxml")));
        Scene addProductScene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add a Product");
        stage.setScene(addProductScene);
        stage.show();

    }

    public void toModifyPartPage(ActionEvent actionEvent) throws IOException {
        Part selectedPart = (Part) mainList.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert noSelection = new Alert(Alert.AlertType.CONFIRMATION);
            noSelection.initModality(Modality.NONE);
            noSelection.setHeaderText("Please Select a Part to Modify");
            noSelection.setContentText("Please Select a Part to Modify");
            noSelection.setTitle("Error");
            Optional<ButtonType> confirm = noSelection.showAndWait();
            return;
        }
        ModifyPartController.moveData(selectedPart);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("modifyPartPage.fxml")));
        Scene modifyPartScene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Modify a Part");
        stage.setScene(modifyPartScene);
        stage.show();

    }

    public void toModifyProductPage(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = (Product) prodMainView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert noSelection = new Alert(Alert.AlertType.CONFIRMATION);
            noSelection.initModality(Modality.NONE);
            noSelection.setHeaderText("Please Select a Part to Modify");
            noSelection.setContentText("Please Select a Part to Modify");
            noSelection.setTitle("Error");
            Optional<ButtonType> confirm = noSelection.showAndWait();
            return;
        }
        ModifyProductController.moveData(selectedProduct);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("modifyProductPage.fxml")));
        Scene modifyProductScene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Modify a Product");
        stage.setScene(modifyProductScene);
        stage.show();

    }


    public void searchParts(ActionEvent actionEvent) {
        String q = partSearchBar.getText();
        ObservableList<Part> productQuery = Inventory.searchByPartName(q);
        if (productQuery.size() < 1) {
            try {
                int iDNum = Integer.parseInt(q);
                Part lookupID = Inventory.searchByPartID(iDNum);
                if (lookupID != null)
                    productQuery.add(lookupID);
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initModality(Modality.NONE);
                    alert.setHeaderText("Part Not Found");
                    alert.setTitle("Part Not Found");
                    alert.setTitle("Error");
                    Optional<ButtonType> confirm = alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.NONE);
                alert.setHeaderText("Part Not Found");
                alert.setTitle("Part Not Found");
                alert.setTitle("Error");
                Optional<ButtonType> confirm = alert.showAndWait();
                return;

            }
        }

        mainList.setItems(productQuery);


    }

    public void searchProduct(ActionEvent actionEvent) {
        String q = productSearchBar.getText();
        ObservableList<Product> search = Inventory.searchByProductName(q);
        if (search.size() < 1) {
            try {
                int iDNum = Integer.parseInt(q);
                Product lookupID = Inventory.searchByProductID(iDNum);
                if (lookupID != null)
                    search.add(lookupID);
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initModality(Modality.NONE);
                    alert.setHeaderText("Product Not Found");
                    alert.setTitle("Product Not Found");
                    alert.setTitle("Error");
                    Optional<ButtonType> confirm = alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException j) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.NONE);
                alert.setHeaderText("Product Not Found");
                alert.setTitle("Product Not Found");
                alert.setTitle("Error");
                Optional<ButtonType> confirm = alert.showAndWait();
                return;
            }
        }

        prodMainView.setItems(search);


    }

    public void onQuit(ActionEvent actionEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.initModality(Modality.NONE);
        confirmationAlert.setTitle("Confirm that you want to Quit");
        confirmationAlert.setContentText("Please confirm that you want to Quit");
        confirmationAlert.setHeaderText("Confirm");
        Optional<ButtonType> confirm = confirmationAlert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            Platform.exit();
        }

    }


    public void deletePart(ActionEvent actionEvent) {
        Part selectedPart = mainList.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert noSelection = new Alert(Alert.AlertType.CONFIRMATION);
            noSelection.initModality(Modality.NONE);
            noSelection.setHeaderText("Please Select a Part to Delete");
            noSelection.setContentText("Please Select a Part to Delete");
            noSelection.setTitle("Error");
            Optional<ButtonType> confirm = noSelection.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm that you want to Delete");
        alert.setContentText("Please confirm that you want to Delete");
        alert.setHeaderText("Confirm Deletion");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
            mainList.setItems(Inventory.getAllParts());
        }
    }

    public void deleteProduct(ActionEvent actionEvent) {
        Product selectedProduct = prodMainView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert noSelection = new Alert(Alert.AlertType.CONFIRMATION);
            noSelection.initModality(Modality.NONE);
            noSelection.setHeaderText("Please Select a Part to Delete");
            noSelection.setContentText("Please Select a Part to Delete");
            noSelection.setTitle("Error");
            Optional<ButtonType> confirm = noSelection.showAndWait();
            return;
        }
        if (!selectedProduct.getAllAssociatedParts(selectedProduct).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Product Contains Associated Parts");
            alert.setContentText("Cannot Delete Because Product Contains Associated Parts");
            alert.setHeaderText("Remove Associated Parts and Try Again");
            Optional<ButtonType> confirm = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Confirm that you want to Delete");
            alert.setContentText("Please confirm that you want to Delete");
            alert.setHeaderText("Confirm Deletion");
            Optional<ButtonType> confirm = alert.showAndWait();
                    if (confirm.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
                prodMainView.setItems(Inventory.getAllProducts());
            }
        }
    }
}







