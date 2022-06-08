package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.HistogramData;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ViewReports {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button MainMenuButton;

    @FXML
    private AnchorPane anchor;

    @FXML
    private GridPane gridCatalog;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox vbox_main;

    HistogramData data;

    //@Subscribe
    public void onUserListDataRecievedEvent(ReceivedComplaintsReport event) {
        System.out.println("Received complaints report\n");
        data = event.getData();
        buildHistogram();
    }

    private void buildHistogram() {

        LocalDate today = LocalDate.now();
        List<String> xAxis = new ArrayList<>();
        xAxis.add(today.toString());
        for (int i = 1; i < data.complaintsNumber.size(); i++) {
            today = today.minusDays(1);
            xAxis.add(today.toString());
        }
        CategoryAxis x    = new CategoryAxis();
        x.setLabel("Days");
        NumberAxis y = new NumberAxis();
        y.setLabel("Complaints Count");
        BarChart bc = new BarChart(x, y);
        bc.getData().add(new XYChart.Data("Samsung", 33));
        bc.getData().add(new XYChart.Data("Xiaomi"  , 25));
        bc.getData().add(new XYChart.Data("Honor"  , 10));
        //for (int i = 0; i < data.complaintsNumber.size(); i++) {
        //    bc.getData().add(new XYChart.Data(xAxis.get(i), data.complaintsNumber.get(i)));
        //}
    }

    @FXML
    void goTo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        //EventBus.getDefault().register(this);
        //System.out.println("Sending request to get complaints");
        //SimpleClient.getClient().requestComplaintsReport();
        //System.out.println("Sent request to get complaints");
        data = new HistogramData();
        data.complaintsNumber.add(1);
        data.complaintsNumber.add(2);
        data.complaintsNumber.add(3);
        data.complaintsNumber.add(4);
        data.complaintsNumber.add(5);
        data.complaintsNumber.add(6);
        data.complaintsNumber.add(7);
        buildHistogram();
    }

}
