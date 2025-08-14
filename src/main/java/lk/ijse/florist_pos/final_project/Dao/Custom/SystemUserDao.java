package lk.ijse.florist_pos.final_project.Dao.Custom;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.florist_pos.final_project.Dao.CrudDao;
import lk.ijse.florist_pos.final_project.Entity.SystemUser;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SystemUserDao extends CrudDao<SystemUser> {

//    void validateLogin(TextField username, PasswordField password, Label label) ;

    boolean updateUserPassword(String userId, String newPassword);

    boolean validateUserForPasswordReset(String id, String userName, String role, String mobile, String email, String nic) ;

     ResultSet validateLogin(String username, String password) throws SQLException ;


}
