package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.hibernate.criterion.Order;
import tornadofx.control.DateTimePicker;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderMenu {

    public RadioButton delivery;
    public RadioButton pickup;
    public Label error;
    @FXML
    private Button ConfirmOrder;
    @FXML
    private DateTimePicker dateTimePrompt;
    @FXML
    private Button MainMenuButton;

    @FXML
    private Text OutputText;

    @FXML
    private ToggleGroup group1;

    @FXML
    private Label total;

    @Subscribe
    public void onRecievedNewUserBalanceData(RecievedNewUserBalanceData recievedNewUserBalanceData){
        App.userData.balance = recievedNewUserBalanceData.newUserBalanceData.balance;
        System.out.println("Recieved new balance: " + App.userData.balance);
    }

    @FXML
    void doConfirmOrder(ActionEvent event) {
        //if neither pickup nor delivery is selected
        if(!delivery.isSelected() && !pickup.isSelected()){
            error.setText("Please select delivery or pickup");
            return;
        }
        OrderData order = new OrderData(App.orderData.items, "", App.userData, App.orderData.totalPrice, LocalDateTime.now(), App.userData.getBranchName(), dateTimePrompt.getDateTimeValue(), App.orderData.totalPriceAfterDiscount);
        System.out.format("Sending Order: %s\n", order.toString());
        SimpleClient.getClient().MakeOrder(order);
        System.out.println("Order sent to server");
        OutputText.setText("Order Confirmed!, Thank you!");
        error.setVisible(false);
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
        EventBus.getDefault().register(this);
        assert ConfirmOrder != null : "fx:id=\"ConfirmOrder\" was not injected: check your FXML file 'OrderMenu.fxml'.";
        assert MainMenuButton != null : "fx:id=\"MainMenuButton\" was not injected: check your FXML file 'OrderMenu.fxml'.";
        assert OutputText != null : "fx:id=\"OutputText\" was not injected: check your FXML file 'OrderMenu.fxml'.";
        assert group1 != null : "fx:id=\"group1\" was not injected: check your FXML file 'OrderMenu.fxml'.";
        assert total != null : "fx:id=\"total\" was not injected: check your FXML file 'OrderMenu.fxml'.";
        //error color red
        error.setStyle("-fx-text-fill: red;");
        total.setText("Total: " + App.orderData.totalPrice + "$");
    }
}
