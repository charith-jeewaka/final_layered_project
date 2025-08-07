package lk.ijse.florist_pos.final_project.Entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private int supplierId;
    private String supplierName;
    private String supplierEmail;
    private String contact;
}
