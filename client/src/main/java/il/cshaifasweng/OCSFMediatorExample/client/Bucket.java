package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Bucket {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button MainMenuButton;

    @FXML
    void goToMainMenu(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert MainMenuButton != null : "fx:id=\"MainMenuButton\" was not injected: check your FXML file 'Bucket.fxml'.";

    }

}
