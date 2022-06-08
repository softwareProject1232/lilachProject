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
        //App.setRoot("MainMenu");
    }

    @FXML
    void updateSettings(ActionEvent event) throws IOException {
        SimpleClient client = SimpleClient.getClient(ipTF.getText(),Integer.parseInt(portTF.getText()));

        client.openConnection();
        App.setRoot("LoginAndRegister");
        //add action
    }

    @FXML
    void initialize() {
        assert ipTF != null : "fx:id=\"ipTF\" was not injected: check your FXML file 'Settings.fxml'.";
        assert portTF != null : "fx:id=\"portTF\" was not injected: check your FXML file 'Settings.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'Settings.fxml'.";

        portTF.setText("3024");
        ipTF.setText("127.0.0.1");
    }


}
