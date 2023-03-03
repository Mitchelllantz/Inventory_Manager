package mitchell.project.software_1;
/**This is the class file for the controller for the Add Part page of the inventory app
 * @author Mitchell Lantz
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    public static int lastId = 10020;
    public RadioButton radioButInHouse;
    public ToggleGroup radioButtonsForInHouseVsOut;
    public RadioButton radioButOutsourced;
    public Label myLabel;
    public TextField addPartNameField;
    public TextField addPartInvField;
    public TextField addPartPriceField;
    public TextField addPartMaxField;
    public TextField addPartMachineIDField;
    public TextField addPartMinField;
    public Button addPartSaveButton;
    public Button addPartCancelButton;
    public TextField addPartIdField;
    public TextField addPartCompanyName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lastId += 10;
        String text = Integer.toString(lastId);
        addPartIdField.setText(text);
    }

    public void toggleLabel(ActionEvent actionEvent) {
        if (radioButInHouse.isSelected()) {
            myLabel.setText("Machine ID");
            addPartCompanyName.setVisible(false);
            addPartMachineIDField.setVisible(true);
            addPartMachineIDField.setPromptText("Type Machine ID");
        } else {
            myLabel.setText("Company Name");
            addPartMachineIDField.setVisible(false);
            addPartCompanyName.setVisible(true);
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

        if(confirm.get() == ButtonType.OK){
        Parent partAddScreenCancel = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
        Scene scene = new Scene(partAddScreenCancel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
        lastId -= 10;
    }

    }

    public void onAddPart(ActionEvent actionEvent) throws IOException {
        String partNameS = addPartNameField.getText();
        String partPriceS = addPartPriceField.getText();
        String partStockS = addPartInvField.getText();
        String partMinS = addPartMinField.getText();
        String partMaxS = addPartMaxField.getText();
        String partMachIdS = addPartMachineIDField.getText();
        String partCompanyNameS = addPartCompanyName.getText();

        if(partNameS.isBlank()){
            Alert nameTFError = new Alert(Alert.AlertType.CONFIRMATION);
            nameTFError.initModality(Modality.NONE);
            nameTFError.setHeaderText("Part Name Field is Blank");
            nameTFError.setContentText("Part Name Field is Blank");
            nameTFError.setTitle("Error");
            Optional<ButtonType> confirm = nameTFError.showAndWait();
            return;
        }

        if(addPartCompanyName.isVisible() && partCompanyNameS.isBlank()){
            Alert companyNameTFError = new Alert(Alert.AlertType.CONFIRMATION);
            companyNameTFError.initModality(Modality.NONE);
            companyNameTFError.setHeaderText("Company Name Field is Blank");
            companyNameTFError.setContentText("Company Name Field is Blank");
            companyNameTFError.setTitle("Error");
            Optional<ButtonType> confirm = companyNameTFError.showAndWait();
            return;

        }
        double price = 0.0;
        try {
             price = Double.parseDouble(partPriceS);
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

        int stock = 0;
        try {
            stock = Integer.parseInt(partStockS);
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
        int min = 0;
        try {
            min = Integer.parseInt(partMinS);
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
        int max = 0;
        try {
            max = Integer.parseInt(partMaxS);
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
        int machId = 0;
        if(radioButInHouse.isSelected()) {
            try {
                machId = Integer.parseInt(partMachIdS);
            } catch (NumberFormatException e) {
                Alert machIdWrongType = new Alert(Alert.AlertType.CONFIRMATION);
                machIdWrongType.initModality(Modality.NONE);
                machIdWrongType.setHeaderText("Machine Number must be a Number");
                machIdWrongType.setTitle("Machine Number must be a Number");
                machIdWrongType.setTitle("Error");
                Optional<ButtonType> confirm = machIdWrongType.showAndWait();
                return;
            }
        }
        if(min <= max && stock <= max && stock >= min && (radioButInHouse.isSelected() || radioButOutsourced.isSelected())) {

            if (radioButInHouse.isSelected()) {
                InHouse inhouse = new InHouse((lastId), partNameS, price, stock, min, max, machId);
                Inventory.addPart(inhouse);
                Parent inventoryScreen = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
                Scene scene = new Scene(inventoryScreen);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            } else if(radioButOutsourced.isSelected()) {
                Outsourced outsourced = new Outsourced((lastId), partNameS, price, stock, min, max, partCompanyNameS);
                Inventory.addPart(outsourced);
                Parent inventoryScreen = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
                Scene scene = new Scene(inventoryScreen);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }
        } else if(max < min){
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
        } else if (!radioButInHouse.isSelected() && !radioButOutsourced.isSelected()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setHeaderText("Must Select if Part is Made in House or Outsourced");
            alert.setTitle("Must Select if Part is Made in House or Outsourced");
            alert.setTitle("Error");
            Optional<ButtonType> confirm = alert.showAndWait();
            return;

        }


    }


}