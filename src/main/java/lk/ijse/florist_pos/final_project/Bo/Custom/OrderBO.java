package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.dto.OrderDetailsDto;
import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {

    boolean placeOrder(List<OrderDetailsDto> orderDetailsList) throws SQLException ;

}
