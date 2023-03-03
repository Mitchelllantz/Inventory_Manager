module mitchell.project.software_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens mitchell.project.software_1 to javafx.fxml;
    exports mitchell.project.software_1;
}