package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;
import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
import il.cshaifasweng.OCSFMediatorExample.server.User;
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
    public Button guest_login;
    @FXML
    public Label label_branch_login;
    @FXML
    public ComboBox branch_list_login;
    public ComboBox branch_list_register;
    public TextField textfield_id_register;
    public Label error;
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

    private List<String> branches;

    @Subscribe
    public void onLoginRecievedEvent(LoginReceivedEvent event) {
        System.out.println("Received login\n");
        error.setText("");
        if (event.didSuccessfullyLogin()){
            App.userData = event.getUser();
            App.orderData = new OrderData();
            try{
                System.out.println("Logged in as " + App.userData.getUsername());
                App.setRoot("MainMenu");
            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        else{
            error.setText("ERROR: User already logged in or wrong username/password/branch");
            System.out.println("Failed to login\n");
        }

    }
    @Subscribe
    public void onBranchRecievedEvent(BranchesReceivedEvent event) {
        System.out.println("Received branch\n");
        branches = event.getBranches().getBranchList();
        vbox_main.setVisible(true);
        for (String branch : branches) {
            branch_list_login.getItems().add(branch);
            branch_list_register.getItems().add(branch);
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
                "Subscription"
        );
        error.setStyle("-fx-text-fill: red;");
        System.out.println("Sending Branches Request");
        SimpleClient.getClient().requestBranches();
        System.out.println("Sent Branches Request");
    }

    public void login(ActionEvent actionEvent) {
        //check that all fields are filled out and that branches are selected
        if (textfield_username_login.getText().isEmpty() || textfield_password_login.getText().isEmpty() || branch_list_login.getSelectionModel().isEmpty()) {
            System.out.println("Missing fields\n");
            error.setText("ERROR: Missing fields");
            return;
        }
        System.out.println("Sending request\n");
        SimpleClient.getClient().requestLogin(textfield_username_login.getText(), textfield_password_login.getText(), branch_list_login.getSelectionModel().getSelectedItem().toString());
        System.out.println("Sent request\n");
    }

    public void login_as_guest(ActionEvent actionEvent) {
        App.userData = new UserData();
        try{
            App.setRoot("PrimaryCatalog");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void register(ActionEvent actionEvent) {
        //check that all fields are filled out and that branches are selected and also that type is selected
        if (textfield_username_register.getText().isEmpty()
                || textfield_password_register.getText().isEmpty() || textfield_email_register.getText().isEmpty()
                || textfield_credit_card_register.getText().isEmpty() || branch_list_register.getSelectionModel().isEmpty()
                || type_dropdown.getSelectionModel().isEmpty() || textfield_id_register.getText().isEmpty()) {
            System.out.println("Missing fields\n");
            return;
        }
        SimpleClient.getClient().requestRegister(new UserData(textfield_username_register.getText(), textfield_password_register.getText(), textfield_email_register.getText(),
                type_dropdown.getSelectionModel().getSelectedIndex() + 1, textfield_credit_card_register.getText(), textfield_id_register.getText(), -1, branch_list_register.getSelectionModel().getSelectedItem().toString(), -1));
        System.out.println("Sending request\n");
        System.out.println("Sent request\n");
    }
}
