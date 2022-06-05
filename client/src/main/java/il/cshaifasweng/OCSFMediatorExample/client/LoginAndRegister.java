package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginAndRegister {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_login;

    @FXML
    private Label label_credit_card_register;

    @FXML
    private Label label_email_register;

    @FXML
    private Label label_password;

    @FXML
    private Label label_password_register;

    @FXML
    private Label label_type_register;

    @FXML
    private Label label_username;

    @FXML
    private Label label_username_register;

    @FXML
    private Button register_login;

    @FXML
    private TextField textfield_credit_card_register;

    @FXML
    private TextField textfield_email_register;

    @FXML
    private TextField textfield_password_login;

    @FXML
    private TextField textfield_password_register;

    @FXML
    private TextField textfield_username_login;

    @FXML
    private TextField textfield_username_register;

    @FXML
    private ComboBox<String> type_dropdown;

    @FXML
    private VBox vbox_main;

    @Subscribe
    public void onLoginRecievedEvent(LoginReceivedEvent event) {
        System.out.format("Received login\n");
        if (event.didSuccessfullyLogin()){
            App.userData = event.getUser();
            App.orderData = new OrderData();
            try{
                System.out.format("Logged in as %s\n", App.userData.getUsername());
                App.setRoot("MainMenu");
            }
            catch(Exception e){
                System.out.format("Error: %s\n", e.getMessage());
            }
        }
        else{
            System.out.format("Failed to login\n");
        }

    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        assert button_login != null : "fx:id=\"button_login\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert label_credit_card_register != null : "fx:id=\"label_credit_card_register\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert label_email_register != null : "fx:id=\"label_email_register\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert label_password != null : "fx:id=\"label_password\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert label_password_register != null : "fx:id=\"label_password_register\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert label_type_register != null : "fx:id=\"label_type_register\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert label_username != null : "fx:id=\"label_username\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert label_username_register != null : "fx:id=\"label_username_register\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert register_login != null : "fx:id=\"register_login\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert textfield_credit_card_register != null : "fx:id=\"textfield_credit_card_register\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert textfield_email_register != null : "fx:id=\"textfield_email_register\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert textfield_password_login != null : "fx:id=\"textfield_password_login\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert textfield_password_register != null : "fx:id=\"textfield_password_register\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert textfield_username_login != null : "fx:id=\"textfield_username_login\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert textfield_username_register != null : "fx:id=\"textfield_username_register\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert type_dropdown != null : "fx:id=\"type_dropdown\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        assert vbox_main != null : "fx:id=\"vbox_main\" was not injected: check your FXML file 'LoginAndRegister.fxml'.";
        type_dropdown.getItems().addAll(
                "Branch",
                "Network",
                "Discount"
        );
    }

    public void login(ActionEvent actionEvent) {
        System.out.format("Sending request\n");
        SimpleClient.getClient().requestLogin(textfield_username_login.getText(), textfield_password_login.getText());
        System.out.format("Sent request\n");
    }
}
