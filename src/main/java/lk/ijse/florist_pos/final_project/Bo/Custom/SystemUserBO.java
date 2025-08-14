package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.dto.SystemUserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface SystemUserBO extends SuperBO {

    boolean saveUser(SystemUserDto systemUserDto) throws SQLException, ClassNotFoundException ;

    Optional<String> login(String username, String password) throws SQLException ;

    boolean validateUserForPasswordReset(String id, String userName, String role, String mobile, String email, String nic) throws SQLException ;

    boolean updateUserPassword(String userId, String newPassword) throws SQLException ;




}
