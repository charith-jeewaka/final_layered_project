package lk.ijse.florist_pos.final_project.controller;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.florist_pos.final_project.Dao.Custom.FlowerDao;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.FlowerDaoImpl;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.OrderDaoImpl;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.PlantDaoImpl;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.StaffDaoImpll;
import lk.ijse.florist_pos.final_project.Dao.Custom.PlantDao;
import lk.ijse.florist_pos.final_project.Dao.Custom.StaffDao;

import java.net.URL;
import java.sql.SQLException;
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
    FlowerDao flowerDao = new FlowerDaoImpl();
    PlantDao plantDao = new PlantDaoImpl();
    StaffDao staffDao = new StaffDaoImpll();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(ancHome.widthProperty());
        imageView.fitHeightProperty().bind(ancHome.heightProperty());
        try {
            double yesterdaySale = OrderDaoImpl.getYesterdayTotalSale();
            lblYesterdaySale.setText(String.valueOf(yesterdaySale));
            double todaySale = OrderDaoImpl.getTodayTotalSale();
            lblTodaySale.setText(String.valueOf(todaySale));
            int totalPlants = plantDao.getTotalPlantQty();
            lblTotalPlants.setText(String.valueOf(totalPlants));
            int totalEmployee = staffDao.getTotalEmployees();
            lblEmployees.setText(String.valueOf(totalEmployee));
            int totalFlowers = flowerDao.getTotalFlowerQty();
            lblTotalFlowers.setText(String.valueOf(totalFlowers));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
