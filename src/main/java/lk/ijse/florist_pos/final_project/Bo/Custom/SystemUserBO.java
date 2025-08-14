package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.dto.SystemUserDto;
import java.sql.SQLException;

public interface SystemUserBO extends SuperBO {

    boolean saveUser(SystemUserDto systemUserDto) throws SQLException, ClassNotFoundException ;

}
