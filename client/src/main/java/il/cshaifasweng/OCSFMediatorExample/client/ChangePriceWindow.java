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
    void updateValues(ActionEvent event) {
        SimpleClient myclient=SimpleClient.getClient();
        myclient.changePrice(Integer.parseInt(PriceTF.getText()),App.data.itemsdata.get(App.thisitem));
        myclient.changeDescription(descritionTF.getText(),App.data.itemsdata.get(App.thisitem));
        myclient.changeName(nameTF.getText(),App.data.itemsdata.get(App.thisitem));
                                                                           myclient.changePriceAfterDiscount(Integer.parseInt(discountTF.getText()),App.data.itemsdata.get(App.thisitem));

    }
    @FXML
    void removefromCatalog(ActionEvent event) {
        SimpleClient myclient=SimpleClient.getClient();
        myclient.removeItem(App.data.itemsdata.get(App.thisitem));
    }
    @FXML
    void initialize() {
        PriceTF.setText(String.valueOf(App.data.itemsdata.get(App.thisitem).getPrice()));
        descritionTF.setText(App.data.itemsdata.get(App.thisitem).getDescription());
        nameTF.setText(App.data.itemsdata.get(App.thisitem).getName());
        discountTF.setText(String.valueOf(App.data.itemsdata.get(App.thisitem).getPriceAfterDiscount()));

    }

}








