package mitchell.project.software_1;
/**This is the main class for the inventory app that launches the program starting with the inventory page FXML JavaFX scene
 *
 * @author Mitchell Lantz
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This is the main class for the inventory app that will launch the rest of the program
 */

public class Main extends Application {

    /**
     *
     * @param stage
     * @throws IOException
     * This start method also loads the test data into the tables, it calls the addTestData method from the Inventory Class
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inventoryPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Inventory.addTestData();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();


    }

    /**
     *
     * @param args
     */

    public static void main(String[] args) {

        launch();

    }
}