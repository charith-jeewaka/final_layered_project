package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;

import lk.ijse.florist_pos.final_project.Dao.Custom.SystemUserDao;
import lk.ijse.florist_pos.final_project.Entity.SystemUser;
import lk.ijse.florist_pos.final_project.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SystemUserDaoImpl implements SystemUserDao {

    @Override
    public ResultSet validateLogin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM system_user WHERE user_name = ? AND password = ?";
        return CrudUtil.execute(sql, username, password);
    }



    public boolean validateUserForPasswordReset(String id, String userName, String role, String mobile, String email, String nic) {
        try {
            String sql = "SELECT * FROM system_user WHERE user_id = ? AND user_name = ? AND user_role = ? AND user_mobile = ? AND user_email = ? AND user_nic = ?";
            ResultSet resultSet = CrudUtil.execute(sql, id, userName, role, mobile, email, nic);
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserPassword(String userId, String newPassword) {
        try {
            String sql = "UPDATE system_user SET password = ? WHERE user_id = ?";
            return CrudUtil.execute(sql, newPassword, userId);
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




