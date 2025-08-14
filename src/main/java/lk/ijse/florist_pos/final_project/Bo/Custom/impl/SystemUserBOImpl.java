package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.SystemUserBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.SystemUserDao;
import lk.ijse.florist_pos.final_project.Dao.DaoFactory;
import lk.ijse.florist_pos.final_project.Entity.SystemUser;
import lk.ijse.florist_pos.final_project.dto.SystemUserDto;

import java.sql.SQLException;

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

}
