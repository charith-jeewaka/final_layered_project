package lk.ijse.florist_pos.final_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lk.ijse.florist_pos.final_project.Bo.BOFactory;
import lk.ijse.florist_pos.final_project.Bo.Custom.SystemUserBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.SystemUserDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class LoginScreenController implements Initializable {
    @FXML
    public TextField txtUserName;
    @FXML
    public PasswordField txtPassword;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnLoggin;
    @FXML
    public Label lblIncorrectMassage;
    @FXML
    public Button btnForgetPassword;
    @FXML
    public BorderPane AncLoginContainer;

    SystemUserBO systemUserBO = (SystemUserBO)
            BOFactory.getInstance().getBo(BOFactory.BoTypes.SYSTEM_USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUserName.setOnAction(e -> txtPassword.requestFocus());
        txtPassword.setOnAction(e -> btnLoggin.fire());
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void clearText() {
        try {
            txtPassword.clear();
        } catch (Exception e) {
            System.out.println("hi");
        }
    }

    public void forgetPasswordOnAction(ActionEvent actionEvent) {
        navigateTo("/View/ForgetPassword.fxml");
    }

    private  void navigateTo(String path) {
        try {
            AncLoginContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(AncLoginContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(AncLoginContainer.heightProperty());

            AncLoginContainer.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    public void btnLogginOnAction(ActionEvent actionEvent) {
        if (!txtUserName.getText().isBlank() && !txtPassword.getText().isBlank()) {
            try {
                Optional<String> loggedInUser = systemUserBO.login(txtUserName.getText(), txtPassword.getText());

                if (loggedInUser.isPresent()) {
                    lblIncorrectMassage.setText("Login successful!");
                    navigateToDashboard(loggedInUser.get());
                } else {
                    lblIncorrectMassage.setText("Invalid username or password");
                    clearText();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                lblIncorrectMassage.setText("Database error!");
            }
        } else {
            lblIncorrectMassage.setText("Enter username and password");
            clearText();
        }
    }

    private void navigateToDashboard(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Dashboard.fxml"));
            Parent root = loader.load();
            DashboardController dashboardController = loader.getController();
            dashboardController.lblCurrentUser.setText(username);
            dashboardController.lblDashBoardName.setText(username);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) txtUserName.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


