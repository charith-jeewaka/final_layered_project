package lk.ijse.florist_pos.final_project.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.florist_pos.final_project.Bo.BOFactory;
import lk.ijse.florist_pos.final_project.Bo.Custom.FlowerBO;
import lk.ijse.florist_pos.final_project.Bo.Custom.OrderBO;
import lk.ijse.florist_pos.final_project.Bo.Custom.PlantBO;
import lk.ijse.florist_pos.final_project.Bo.Custom.StaffBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.OrderDaoImpl;
import lk.ijse.florist_pos.final_project.Dao.Custom.OrderDao;
import lk.ijse.florist_pos.final_project.dto.Tm.OrderStats;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public AnchorPane ancHome;
    public ImageView imageView;
    public Label lblYesterdaySale;
    public Label lblTodaySale;
    public Label lblTotalFlowers;
    public Label lblTotalPlants;
    public Label lblEmployees;
    public BarChart<String, Number> barChart;
    public CategoryAxis barDates;
    public NumberAxis barSales;

    @FXML
    public PieChart ordersPieChart;


    FlowerBO flowerBO =
            (FlowerBO) BOFactory.getInstance().getBo(BOFactory.BoTypes.FLOWER);

    PlantBO plantBO =
            (PlantBO)BOFactory.getInstance().getBo(BOFactory.BoTypes.PLANT);

    StaffBO staffBO =
            (StaffBO) BOFactory.getInstance().getBo(BOFactory.BoTypes.STAFF);

    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBo(BOFactory.BoTypes.ORDER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(ancHome.widthProperty());
        imageView.fitHeightProperty().bind(ancHome.heightProperty());

        loadPieChartData();
        try {
            double yesterdaySale = orderBO.getYesterdayTotalSaleForDashBoard();
            lblYesterdaySale.setText(String.valueOf(yesterdaySale));
            double todaySale = orderBO.getTodayTotalSaleForDashBoard();
            lblTodaySale.setText(String.valueOf(todaySale));
            int totalPlants = plantBO.getTotalPlantQty();
            lblTotalPlants.setText(String.valueOf(totalPlants));
            int totalEmployee = staffBO.getTotalEmployees();
            lblEmployees.setText(String.valueOf(totalEmployee));
            int totalFlowers = flowerBO.getTotalFlowerQty();
            lblTotalFlowers.setText(String.valueOf(totalFlowers));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPieChartData() {
        try {
            List<OrderStats> stats = orderBO.getOrderCountsByCustomer();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            for (OrderStats stat : stats) {
                pieChartData.add(new PieChart.Data(stat.getCustomerName(), stat.getOrderCount()));
            }

            ordersPieChart.setData(pieChartData);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}


