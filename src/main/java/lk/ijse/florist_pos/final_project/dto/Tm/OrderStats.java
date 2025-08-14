package lk.ijse.florist_pos.final_project.dto.Tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderStats {
    private String customerName;
    private int orderCount;

//    public OrderStats(String customerName, int orderCount) {
//        this.customerName = customerName;
//        this.orderCount = orderCount;
//    }
//
//    public String getCustomerName() { return customerName; }
//    public int getOrderCount() { return orderCount; }
}
