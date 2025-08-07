package lk.ijse.florist_pos.final_project.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetails {

    private String orderId;
    private String customerName;
    private String itemName;
    private String itemId;
    private String paymentType;
    private String itemQty;
    private String totalAmount;
    private String handleBy;

    private String totalBill;
}

