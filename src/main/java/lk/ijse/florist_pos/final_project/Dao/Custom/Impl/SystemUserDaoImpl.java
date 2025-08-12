package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.Dao.Custom.SystemUserDao;
import lk.ijse.florist_pos.final_project.Entity.SystemUser;
import lk.ijse.florist_pos.final_project.controller.DashboardController;
import lk.ijse.florist_pos.final_project.controller.LoginScreenController;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SystemUserDaoImpl implements SystemUserDao {
    public void validateLogin(TextField username, PasswordField password, Label label) {
        LoginScreenController loginScreenController = new LoginScreenController();
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "SELECT * FROM system_user WHERE user_name = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username.getText());
            statement.setString(2, password.getText());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                label.setText("Login successful!");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Dashboard.fxml"));
                Parent root = loader.load();
                DashboardController dashboardController = loader.getController();
                dashboardController.lblCurrentUser.setText(resultSet.getString("user_name"));
                dashboardController.lblDashBoardName.setText(resultSet.getString("user_name"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();


                ((Stage) username.getScene().getWindow()).close();

            } else {

                label.setText("Invalid username or password");
                username.clear();
                password.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
            label.setText("Database error!");
        }

        loginScreenController.clearText();
    }


    @Override
    public boolean validateUserForPasswordReset(String id, String userName, String role, String mobile, String email, String nic) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "SELECT * FROM system_user WHERE user_id = ? AND user_name = ? AND user_role = ? AND user_mobile = ? AND user_email = ? AND user_nic = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, userName);
            statement.setString(3, role);
            statement.setString(4, mobile);
            statement.setString(5, email);
            statement.setString(6, nic);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserPassword(String userId, String newPassword) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "UPDATE system_user SET password = ? WHERE user_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, userId);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(SystemUser systemUser) throws SQLException {
        String sql = "INSERT INTO system_user (user_name, password, user_role, user_mobile, user_email, user_nic) VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                systemUser.getUserName(),
                systemUser.getPassword(),
                systemUser.getUserRole(),
                systemUser.getUserMobile(),
                systemUser.getUserEmail(),
                systemUser.getUserNic()
        );
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<SystemUser> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(SystemUser entity) throws SQLException {
        return false;
    }

    @Override
    public SystemUser search(String number) throws SQLException {
        return null;
    }

}




