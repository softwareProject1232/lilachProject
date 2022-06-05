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
    void Purchase(ActionEvent event) {

    }

    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }

    void buildBucket(){
        cleanBucket();
        int id;
        String name;
        int price;
        String description;
        HBox pane;
        int index = 0;
        sum = 0;
        for (BasketItemData item: App.orderData.items){
            id = item.listItems.get(0).getId();
            name = item.listItems.get(0).getName();
            price = item.listItems.get(0).getPrice();
            description = item.listItems.get(0).getDescription();
            System.out.format("id: %s\nname: %s\nprice: %s\ndescription: %s\n", id, name, price, description);
            pane = generateItem(id, name, price, description,index);
            index++;
            sum += price;
            addItem(pane);
        }
        total.setText("Total: " + sum + "$");
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
        assert ItemList != null : "fx:id=\"ItemList\" was not injected: check your FXML file 'Bucket.fxml'.";
        assert MainMenuButton != null : "fx:id=\"MainMenuButton\" was not injected: check your FXML file 'Bucket.fxml'.";
        assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'Bucket.fxml'.";
        assert scroll != null : "fx:id=\"scroll\" was not injected: check your FXML file 'Bucket.fxml'.";
        assert total != null : "fx:id=\"total\" was not injected: check your FXML file 'Bucket.fxml'.";

        buildBucket();
    }

}
