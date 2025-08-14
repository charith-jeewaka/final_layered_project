package lk.ijse.florist_pos.final_project.util;
import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.OrderDaoImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class ReportGenerator {

    public void generateTodaySalesReport() {
        try {
            //  Get todays total income from model
            BigDecimal todayIncome = OrderDaoImpl.getTodayTotalSales();
            String todayIncomeString = todayIncome.toString();
            System.out.println(todayIncomeString);

            //  Load compiled Jasper file
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(
                    getClass().getResource("/Reports/tsales.jasper")
            );

            //  Prepare parameters
            HashMap<String, Object> params = new HashMap<>();
            params.put("TotalIncome", todayIncome);

            // Fill the report
            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);

            //  Sshow report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }
    }

    public void generateYesterdaySalesReport() {
        try {
            BigDecimal yesterdayIncome = OrderDaoImpl.getYesterdayTotalSales();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(
                    getClass().getResource("/Reports/ysales.jasper")
            );

            HashMap<String, Object> params = new HashMap<>();
            params.put("YTotalIncome", yesterdayIncome);

            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }
    }

    public void generateBill(String balance) {
        try {

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(
                    getClass().getResource("/Reports/Bill.jasper")
            );

            HashMap<String, Object> params = new HashMap<>();
            params.put("Balance", balance);

            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }
    }
}
