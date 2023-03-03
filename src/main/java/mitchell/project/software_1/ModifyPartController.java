package mitchell.project.software_1;
/**This is the class file for the controller of the modify part page of the inventory app
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

public class  ModifyPartController implements Initializable {

    @FXML
    public RadioButton radioButInHouse;
    @FXML
    public ToggleGroup radioButtonsForInHouseVsOut;
    @FXML
    public RadioButton radioButOutsourced;
    @FXML
    public Label myLabel;
    @FXML
    public TextField modPartNameField;
    @FXML
    public TextField modPartInvField;
    @FXML
    public TextField modPartPriceField;
    @FXML
    public TextField modPartMaxField;
    @FXML
    public TextField modPartMachineIDField;
    @FXML
    public TextField modPartMinField;
    @FXML
    public Button modPartSaveButton;
    @FXML
    public Button modPartCancelButton;
    @FXML
    public TextField modPartIdField;
    @FXML
    public TextField modPartCompanyName;
    private static Part partData;

    public static void moveData(Part part){
        partData = part;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modPartIdField.setText(String.valueOf(partData.getId()));
        modPartNameField.setText(String.valueOf(partData.getName()));
        modPartPriceField.setText(String.valueOf(partData.getPrice()));
        modPartMinField.setText(String.valueOf(partData.getMin()));
        modPartMaxField.setText(String.valueOf(partData.getMax()));
        modPartInvField.setText(String.valueOf(partData.getStock()));

        if(partData instanceof Outsourced){
            modPartCompanyName.setVisible(true);
            radioButOutsourced.setSelected(true);
            modPartCompanyName.setText(((Outsourced) partData).getCompanyName());
            myLabel.setText("Company Name");
        }
        else if(partData instanceof InHouse){
            modPartMachineIDField.setVisible(true);
            radioButInHouse.setSelected(true);
            modPartMachineIDField.setText(String.valueOf(((InHouse) partData).getMachineId()));
            myLabel.setText("Machine ID");

        }
    }
    public void toggleLabel(ActionEvent actionEvent) {
        if (radioButInHouse.isSelected()) {
            myLabel.setText("Machine ID");
            modPartCompanyName.setVisible(false);
            modPartMachineIDField.setVisible(true);
            modPartMachineIDField.setPromptText("Type Machine ID");
        } else {
            myLabel.setText("Company Name");
            modPartMachineIDField.setVisible(false);
            modPartCompanyName.setVisible(true);
        }
    }

    public void onModPart(ActionEvent actionEvent) throws IOException {
        String partIdS = modPartIdField.getText();
        String partNameS = modPartNameField.getText();
        String partPriceS = modPartPriceField.getText();
        String partStockS = modPartInvField.getText();
        String partMinS = modPartMinField.getText();
        String partMaxS = modPartMaxField.getText();
        String partMachIdS = modPartMachineIDField.getText();
        String partCompanyNameS = modPartCompanyName.getText();

        if(partNameS.isBlank()){
            Alert nameTFError = new Alert(Alert.AlertType.CONFIRMATION);
            nameTFError.initModality(Modality.NONE);
            nameTFError.setHeaderText("Part Name Field is Blank");
            nameTFError.setContentText("Part Name Field is Blank");
            nameTFError.setTitle("Error");
            Optional<ButtonType> confirm = nameTFError.showAndWait();
            return;
        }
        if(partIdS.isBlank()){
            Alert iDTFError = new Alert(Alert.AlertType.CONFIRMATION);
            iDTFError.initModality(Modality.NONE);
            iDTFError.setHeaderText("Part ID Field is Blank");
            iDTFError.setContentText("Part ID Field is Blank");
            iDTFError.setTitle("Error");
            Optional<ButtonType> confirm = iDTFError.showAndWait();
            return;
        }

        if(modPartCompanyName.isVisible() && partCompanyNameS.isBlank()){
            Alert companyNameTFError = new Alert(Alert.AlertType.CONFIRMATION);
            companyNameTFError.initModality(Modality.NONE);
            companyNameTFError.setHeaderText("Company Name Field is Blank");
            companyNameTFError.setContentText("Company Name Field is Blank");
            companyNameTFError.setTitle("Error");
            Optional<ButtonType> confirm = companyNameTFError.showAndWait();
            return;

        }
        int id;
        try{
            id = Integer.parseInt(partIdS);
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setHeaderText("ID Input Must be a Number");
            alert.setContentText("ID Input Must be a Number");
            alert.setTitle("Error");
            Optional<ButtonType> confirm = alert.showAndWait();
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
        if(stock <= max && stock >= min && (radioButInHouse.isSelected() || radioButOutsourced.isSelected())) {

            if(partData instanceof InHouse) {
                if(radioButOutsourced.isSelected()){
                    Outsourced outsourced = new Outsourced(id, partNameS, price, stock, min, max, partCompanyNameS);
                    Inventory.addPart(outsourced);
                    Inventory.deletePart(partData);
                    Parent inventoryScreen = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
                    Scene scene = new Scene(inventoryScreen);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setTitle("Inventory Management System");
                    stage.setScene(scene);
                    stage.show();
                    return;
                }
                Inventory.updatePart(partData, id, partNameS, price, stock, min, max);
                ((InHouse) partData).setMachineId(machId);
                Parent inventoryScreen = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
                Scene scene = new Scene(inventoryScreen);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }
            else if(partData instanceof Outsourced) {
                if(radioButInHouse.isSelected()){
                    InHouse inhouse = new InHouse(id, partNameS, price, stock, min, max, machId);
                    Inventory.addPart(inhouse);
                    Inventory.deletePart(partData);
                    Parent inventoryScreen = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
                    Scene scene = new Scene(inventoryScreen);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setTitle("Inventory Management System");
                    stage.setScene(scene);
                    stage.show();
                    return;
                }
                Inventory.updatePart(partData, id, partNameS, price, stock, min, max);
                ((Outsourced) partData).setCompanyName(partCompanyNameS);
                Parent inventoryScreen = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
                Scene scene = new Scene(inventoryScreen);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(max < min){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setHeaderText("Minimum Number must be less than Maximum");
            alert.setTitle("Minimum Number must be less than Maximum");
            alert.setTitle("Error");
            Optional<ButtonType> confirm = alert.showAndWait();
            return;
        }
        else if (stock > max) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setHeaderText("Inventory Amount is Over Maximum Amount");
            alert.setTitle("Inventory Amount is Over Maximum Amount");
            alert.setTitle("Error");
            Optional<ButtonType> confirm = alert.showAndWait();
            return;
        }
        else if (stock < min) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setHeaderText("Inventory Amount is Less than Minimum Amount");
            alert.setTitle("Inventory Amount is Less than Minimum Amount");
            alert.setTitle("Error");
            Optional<ButtonType> confirm = alert.showAndWait();
            return;
        }
        else if (!radioButInHouse.isSelected() && !radioButOutsourced.isSelected()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setHeaderText("Must Select if Part is Made in House or Outsourced");
            alert.setTitle("Must Select if Part is Made in House or Outsourced");
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
            Parent modPartCancel = FXMLLoader.load(getClass().getResource("inventoryPage.fxml"));
            Scene scene = new Scene(modPartCancel);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
        }
    }
}
