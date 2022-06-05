package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenu {

    @FXML
    private VBox CustomerMenu;

    @FXML
    private VBox ManagerMenu;

    @FXML
    void LoadBasket(ActionEvent event) throws IOException {
        App.setRoot("Bucket");
    }

    @FXML
    void LoadCatalog(ActionEvent event) throws IOException {
        App.setRoot("PrimaryCatalog");
    }

    @FXML
    void LoadComplaints(ActionEvent event) throws IOException {
        App.setRoot("Complaints");
    }

    @FXML
    void LoadLogin(ActionEvent event) throws IOException {
        App.setRoot("LoginAndRegister");
    }

    @FXML
    void LoadReports(ActionEvent event) throws IOException {
        App.setRoot("ReportsScreen");
    }


    @FXML
    void initialize() {
        assert CustomerMenu != null : "fx:id=\"CustomerMenu\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert ManagerMenu != null : "fx:id=\"ManagerMenu\" was not injected: check your FXML file 'MainMenu.fxml'.";
        boolean isManager = App.userData.type == 4;
        ManagerMenu.setDisable(!isManager);
        ManagerMenu.setVisible(isManager);
        CustomerMenu.setDisable(isManager);
        CustomerMenu.setVisible(!isManager);

    }
}
