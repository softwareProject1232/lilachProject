package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.io.IOException;

public class OrderMenu {

    @FXML
    private Button ConfirmOrder;

    @FXML
    private Button MainMenuButton;

    @FXML
    private Text OutputText;

    @FXML
    private ToggleGroup group1;

    @FXML
    void doConfirmOrder(ActionEvent event) {
        /**************************************************
        PUT BACKEND STUFF HERE!!!!
         *********************************************/


        OutputText.setText("Order Condirmed!, Thank you!");
        ConfirmOrder.setVisible(false);
        ConfirmOrder.setDisable(true);
    }

    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }

}
