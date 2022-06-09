package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintData;
import il.cshaifasweng.OCSFMediatorExample.server.Complaint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Complaints {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button MainMenuButton;

    @FXML
    private Button SendComplaintButton;

    @FXML
    private TextArea TextField;

    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {

        App.setRoot("MainMenu");
    }

    @FXML
    void sentComplaint(ActionEvent event) {
        SimpleClient myclient=SimpleClient.getClient();
        ComplaintData cmp=new ComplaintData(TextField.getText(),App.userData,0);
        System.out.println(cmp.complaintDescription+","+cmp.issuedBy);
        myclient.sendComplaint(cmp);
        TextField.setText("");
    }

    @FXML
    void initialize() {
        assert MainMenuButton != null : "fx:id=\"MainMenuButton\" was not injected: check your FXML file 'Complaints.fxml'.";
        assert SendComplaintButton != null : "fx:id=\"SendComplaintButton\" was not injected: check your FXML file 'Complaints.fxml'.";
        assert TextField != null : "fx:id=\"TextField\" was not injected: check your FXML file 'Complaints.fxml'.";

    }

}