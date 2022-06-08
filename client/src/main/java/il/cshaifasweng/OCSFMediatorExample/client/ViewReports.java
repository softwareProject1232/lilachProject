package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.HistogramData;
import il.cshaifasweng.OCSFMediatorExample.entities.ReportOrdersByItems;
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

    public GridPane grid;
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

    private int daysBack = 30;

    private BarChart<CategoryAxis, NumberAxis> bc_comp, bc_orders;
    HistogramData data;
    ReportOrdersByItems ordersData;
    @Subscribe
    public void onReceivedReportOrdersByItemsEventEvent(ReceivedReportOrdersByItemsEvent event) {
        System.out.println("Received complaints report\n");
        ordersData = event.getOrders();
        buildOrdersGraph();
    }
    @Subscribe
    public void onReceivedComplaintsReportEvent(ReceivedComplaintsReport event) {
        System.out.println("Received complaints report\n");
        data = event.getData();
        buildComplaintsHistogram();
    }
    private void buildOrdersGraph() {

        List<String> xAxis = new ArrayList<>();
        String name;
        for (int i = 0; i < ordersData.itemsReport.size(); i++) {
            name = ordersData.itemsReport.get(i).itemData.getName();
            xAxis.add(name);
        }
        CategoryAxis x = new CategoryAxis();
        x.setLabel("Items");
        x.setTickLabelRotation(0);
        NumberAxis y = new NumberAxis();
        y.setLabel("Count");
        bc_orders = new BarChart(x, y);
        bc_orders.setTitle("Items orders");
        XYChart.Series series = new XYChart.Series();
        series.setName("Items");
        for (int i = ordersData.itemsReport.size()-1 ; i >= 0; i--) {
            series.getData().add(new XYChart.Data(xAxis.get(i), ordersData.itemsReport.get(i).timesOrdered));
        }
        bc_orders.getData().add(series);
        grid.add(bc_orders, 0, 1);
    }
    private void buildComplaintsHistogram() {

        LocalDate today = LocalDate.now();
        List<String> xAxis = new ArrayList<>();
        xAxis.add(today.toString());
        for (int i = 1; i < data.complaintsNumber.size(); i++) {
            today = today.minusDays(1);
            xAxis.add(today.toString());
        }
        CategoryAxis x    = new CategoryAxis();
        x.setLabel("Days");
        x.setTickLabelRotation(0);
        NumberAxis y = new NumberAxis();
        y.setLabel("Complaints Count");
        bc_comp = new BarChart(x, y);
        bc_comp.setTitle("Complaints per day");

        XYChart.Series series = new XYChart.Series();
        series.setName("Complaints");
        for (int i = data.complaintsNumber.size()-1 ; i >= 0; i--) {
            series.getData().add(new XYChart.Data(xAxis.get(i), data.complaintsNumber.get(i)));
        }
        bc_comp.getData().add(series);
        grid.add(bc_comp, 0, 0);
    }

    @FXML
    void goTo(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        System.out.println("Sending request to get complaints");
        SimpleClient.getClient().requestComplaintsReport(daysBack);
        System.out.println("Sent request to get complaints");
        System.out.println("Sending request to get orders");
        SimpleClient.getClient().requestOrdersReport("network", daysBack);
        System.out.println("Sent request to get orders");
    }

}
