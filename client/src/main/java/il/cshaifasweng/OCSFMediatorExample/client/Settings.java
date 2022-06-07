package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Settings {

    @FXML
    private Button MainMenuButton;

    @FXML
    private TextField ipTF;

    @FXML
    private TextField portTF;

    @FXML
    private Button updateButton;

    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }

    @FXML
    void updateSettings(ActionEvent event) {
        //add action
    }

}
