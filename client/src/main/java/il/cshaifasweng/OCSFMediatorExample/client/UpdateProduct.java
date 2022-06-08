package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class UpdateProduct {

    @FXML
    private Label DescriptionLabel;

    @FXML
    private ImageView flowerimage;

    @FXML
    private Button GoBackButton;

    @FXML
    private Label NameLabel;

    @FXML
    private Label priceText;

    @FXML
    private Button updatePrice;

    @FXML
    void changeToPriceWindow(ActionEvent event) throws IOException {
        try {
            System.out.println("changing root to changepricewindow");
            App.setRoot("ChangePriceWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void switchToCatalogWindow(ActionEvent event) throws IOException {
        App.setRoot("PrimaryCatalog");
    }

    @FXML
    void initialize() {
        NameLabel.setText(App.data.itemsdata.get(App.thisitem).getName());//insert name from data base
        DescriptionLabel.setText(App.data.itemsdata.get(App.thisitem).getDescription());//insert descriptioon from data base
        priceText.setText("Price: "+String.valueOf(App.data.itemsdata.get(App.thisitem).getPrice()));//insert price
        int price=App.data.itemsdata.get(App.thisitem).getPrice();
        int priceAfterDiscount=App.data.itemsdata.get(App.thisitem).getPriceAfterDiscount();
        if(priceAfterDiscount < price)
        {
            priceText.setText("Price: " + price +"$ -> " + priceAfterDiscount + "$");
        }
        String path=App.data.itemsdata.get(App.thisitem).getImageURL();
        Image image = new Image(path, true);
        flowerimage.setImage(image);


        if(App.userData.type!=4){
            updatePrice.setVisible(false);
        }
        else{
            updatePrice.setVisible(true);
        }
    }



}