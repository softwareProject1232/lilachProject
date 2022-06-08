package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChangePriceWindow {

    @FXML
    private TextField PriceTF;

    @FXML
    private TextField descritionTF;

    @FXML
    private TextField nameTF;

    @FXML
    private Button showCatalogButton;

    @FXML
    private Button updateButton;

    @FXML
    private TextField discountTF;

    @FXML
    void showCatalolgWindow(ActionEvent event) throws IOException {
        App.setRoot("PrimaryCatalog");
    }

    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }

    @FXML
    void updateValues(ActionEvent event) throws IOException {
        try {
            int num=Integer.parseInt(PriceTF.getText());
        } catch (NumberFormatException e) {
            PriceTF.setText("Illegal Input");
            return;
        }
        try {
            int num=Integer.parseInt(descritionTF.getText());
        } catch (NumberFormatException e) {
            descritionTF.setText("Illegal Input");
            return;
        }

        SimpleClient myclient=SimpleClient.getClient();
        myclient.changePrice(Integer.parseInt(PriceTF.getText()),App.data.itemsdata.get(App.thisitem));
        myclient.changeDescription(descritionTF.getText(),App.data.itemsdata.get(App.thisitem));
        myclient.changeName(nameTF.getText(),App.data.itemsdata.get(App.thisitem));
                                                                           myclient.changePriceAfterDiscount(Integer.parseInt(discountTF.getText()),App.data.itemsdata.get(App.thisitem));
        App.setRoot("PrimaryCatalog");
    }
    @FXML
    void removefromCatalog(ActionEvent event) throws IOException {
        SimpleClient myclient=SimpleClient.getClient();
        myclient.removeItem(App.data.itemsdata.get(App.thisitem));
        App.setRoot("PrimaryCatalog");
    }
    @FXML
    void initialize() {
        PriceTF.setText(String.valueOf(App.data.itemsdata.get(App.thisitem).getPrice()));
        descritionTF.setText(App.data.itemsdata.get(App.thisitem).getDescription());
        nameTF.setText(App.data.itemsdata.get(App.thisitem).getName());
        discountTF.setText(String.valueOf(App.data.itemsdata.get(App.thisitem).getPriceAfterDiscount()));

    }

}








