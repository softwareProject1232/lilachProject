package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RefundForComplaint {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button refundButton;

    @FXML
    private TextField refundTF;

    @FXML
    void goBackAction(ActionEvent event) throws IOException {
        App.setRoot("ReviewComplaints");
    }

    @FXML
    void refundAction(ActionEvent event) throws IOException {
        try {
            int num=Integer.parseInt(refundTF.getText());
        } catch (NumberFormatException e) {
            refundTF.setText("Illegal input");
            return;
        }

        SimpleClient myclient=SimpleClient.getClient();
        myclient.changeBalance(String.valueOf(App.compuser.balance+Integer.parseInt(refundTF.getText())),App.compuser);
        App.userData.balance=App.userData.balance+Integer.parseInt(refundTF.getText());

        myclient.requestreomvecmplaint(App.thiscomp.id);
    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'RefundForComplaint.fxml'.";
        assert refundButton != null : "fx:id=\"refundButton\" was not injected: check your FXML file 'RefundForComplaint.fxml'.";
        assert refundTF != null : "fx:id=\"refundTF\" was not injected: check your FXML file 'RefundForComplaint.fxml'.";

    }

}
