package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class OrderList {

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
    private Label balance;

    public List<OrderData> currentList;
    @Subscribe
    public void onUserOrderDataRecievedEvent(ReceivedOrderListDataEvent event) {
        currentList = event.getOrders().orders;
        System.out.println("Received user order list of size\n" + currentList.size());
        buildGrid();
    }
    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }
    void buildGrid(){
        cleanOrders();
        int id;
        LocalDateTime date;
        int price;
        HBox pane;
        int index = 0;

        for (OrderData comp: currentList){
            System.out.format("Order: %s\n", comp.toString());
            price = comp.getTotalPrice();
            date = comp.getOrderDate();
            id = comp.getId();
            pane = generateItem(date, price,id);
            index++;
            addItem(pane);
        }
    }

    @Subscribe
    public void onRecievedNewUserBalanceData(RecievedNewUserBalanceData recievedNewUserBalanceData){
        App.userData.balance = recievedNewUserBalanceData.newUserBalanceData.balance;
        balance.setText("Balance: " + App.userData.balance + "$");
        System.out.println("Recieved new balance: " + App.userData.balance);
        SimpleClient.getClient().requestOrdersByUser(App.userData.getDbId(), App.userData.getBranchName());
    }

    void cleanOrders(){
        ItemList.getChildren().removeAll();
        ItemList.getChildren().clear();
    }
    HBox generateItem(LocalDateTime date, int price,int id){
        HBox ret = new HBox();
        Label l_date = new Label(date.toString()), l_price = new Label(String.valueOf(price) + "$");
        Button refund = new Button();
        ret.setAlignment(Pos.CENTER);
        SimpleClient myclient=SimpleClient.getClient();

        l_date.setUnderline(true);
        refund.setText("Resolve");
        refund.setOnMouseClicked(event ->  {;
            System.out.println("Clicked resolve " );
            myclient.requestCancelOrder(id);
        });

        ret.setPrefSize(350, 50);
        ret.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        ret.getChildren().addAll(l_date,l_price,refund);
        return ret;
    }
    @FXML
    void addItem(HBox item) {
        ItemList.getChildren().add(item);
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        System.out.println("Sending request\n");
        SimpleClient.getClient().requestOrdersByUser(App.userData.getDbId(), App.userData.getBranchName());
        System.out.println("Sent request\n");
        balance.setText("Balance: " + String.valueOf(App.userData.balance) + "$");
    }

}
