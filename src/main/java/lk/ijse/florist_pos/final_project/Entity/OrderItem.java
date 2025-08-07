package lk.ijse.florist_pos.final_project.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderItem {
    private String orderItemName;
    private double unitPrice;
    private int quantity;
}
