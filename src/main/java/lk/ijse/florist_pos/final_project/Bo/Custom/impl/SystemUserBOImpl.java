package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.SystemUserBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.SystemUserDao;
import lk.ijse.florist_pos.final_project.Dao.DaoFactory;
import lk.ijse.florist_pos.final_project.Entity.SystemUser;
import lk.ijse.florist_pos.final_project.dto.SystemUserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SystemUserBOImpl implements SystemUserBO {

    SystemUserDao systemUserDao = (SystemUserDao)
            DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.SYSTEM_USER);

    @Override
    public boolean saveUser(SystemUserDto systemUserDto) throws SQLException {
        return systemUserDao.save(
                new SystemUser(
                        systemUserDto.getUserId(),
                        systemUserDto.getUserName(),
                        systemUserDto.getPassword(),
                        systemUserDto.getUserRole(),
                        systemUserDto.getUserMobile(),
                        systemUserDto.getUserEmail(),
                        systemUserDto.getUserNic()
                        )
        );
    }

    @Override
    public Optional<String> login(String username, String password) throws SQLException {
        ResultSet rs = systemUserDao.validateLogin(username, password);
        if (rs.next()) {
            return Optional.of(rs.getString("user_name"));
        }
        return Optional.empty();
    }

    @Override
    public boolean validateUserForPasswordReset(String id, String userName, String role, String mobile, String email, String nic) throws SQLException {
        return systemUserDao.validateUserForPasswordReset(id, userName, role, mobile, email, nic);
    }

    @Override
    public boolean updateUserPassword(String userId, String newPassword) throws SQLException {
        return systemUserDao.updateUserPassword(userId, newPassword);
    }


}
