package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

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
    private Label total;

    @FXML
    void doConfirmOrder(ActionEvent event) {
        /**************************************************
        PUT BACKEND STUFF HERE!!!!
         *********************************************/


        OutputText.setText("Order Condirmed!, Thank you!");
        ConfirmOrder.setVisible(false);
        ConfirmOrder.setDisable(true);
        App.orderData.items.clear();
    }

    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }
    @FXML
    void initialize() {
        assert ConfirmOrder != null : "fx:id=\"ConfirmOrder\" was not injected: check your FXML file 'OrderMenu.fxml'.";
        assert MainMenuButton != null : "fx:id=\"MainMenuButton\" was not injected: check your FXML file 'OrderMenu.fxml'.";
        assert OutputText != null : "fx:id=\"OutputText\" was not injected: check your FXML file 'OrderMenu.fxml'.";
        assert group1 != null : "fx:id=\"group1\" was not injected: check your FXML file 'OrderMenu.fxml'.";
        assert total != null : "fx:id=\"total\" was not injected: check your FXML file 'OrderMenu.fxml'.";

        total.setText("Total: " + App.orderData.totalPrice + "$");
    }
}