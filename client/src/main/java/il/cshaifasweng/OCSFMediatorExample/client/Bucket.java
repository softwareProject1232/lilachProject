package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.BasketItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Bucket {

    public Button purchase_button;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox ItemList;

    @FXML
    private Button MainMenuButton;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Text total;

    private int sum;

    @FXML
    void Purchase(ActionEvent event) throws IOException {
        App.setRoot("OrderMenu");
    }

    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }

    void buildBucket(){
        if (App.orderData.items.size() == 0)
            purchase_button.setDisable(true);
        cleanBucket();
        int id;
        String name;
        int price;
        String description;
        HBox pane;
        int index = 0;
        int zer_count = 0;
        App.orderData.totalPrice = 0;
        for (BasketItemData item: App.orderData.items){
            if(item.listItems.size() == 1)
            {
                id = item.listItems.get(0).getId();
                name = item.listItems.get(0).getName();
                price = item.listItems.get(0).getPrice();
                description = item.listItems.get(0).getDescription();
            }
            else
            {
                zer_count++;
                id = 0;
                name = "Zer " + zer_count;
                price = 0;
                description = "";
                for(ItemData it:item.listItems){
                    price += it.getPrice();
                    description += it.getName() + ", ";
                }
                description.substring(0, description.length() - 2);
            }
            System.out.format("id: %s\nname: %s\nprice: %s\ndescription: %s\n", id, name, price, description);
            pane = generateItem(id, name, price, description,index);
            index++;
            App.orderData.totalPrice += price;
            addItem(pane);
        }
        if(App.orderData.totalPrice >= 50 && App.userData.type != 1)
        {
            total.setText("Discount for buying over 50$\nTotal: " + App.orderData.totalPrice +"$ -> " + (int)(App.orderData.totalPrice*0.9) +"$");
            App.orderData.totalPrice = (int)(App.orderData.totalPrice*0.9);
        }
        else
            total.setText("Total: " + App.orderData.totalPrice + "$");
    }
    void cleanBucket(){
        ItemList.getChildren().removeAll();
        ItemList.getChildren().clear();
    }
    HBox generateItem(int id, String name, int price, String description,int index){
        HBox ret = new HBox();
        Label l_name = new Label(name),l_desc = new Label(description), l_price = new Label("Price: " + price + "$");
        Button b_remove = new Button();
        ret.setAlignment(Pos.CENTER);

        l_name.setUnderline(true);
        b_remove.setText("Remove");
        b_remove.setOnMouseClicked(event ->  {;
            System.out.println("Clicked ID: " + (id-1));
            App.orderData.items.remove(App.orderData.items.get(index));
            buildBucket();
        });

        ret.setPrefSize(350, 50);
        ret.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        ret.getChildren().addAll(l_name,l_desc, l_price,b_remove);
        return ret;
    }
    @FXML
    void addItem(HBox item) {
        ItemList.getChildren().add(item);
    }

    @FXML
    void initialize() {
        if (App.orderData.items.size() == 0)
            purchase_button.setDisable(true);
        buildBucket();
    }

}
