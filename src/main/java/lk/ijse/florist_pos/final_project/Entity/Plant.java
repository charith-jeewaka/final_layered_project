package lk.ijse.florist_pos.final_project.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Plant {
    private String plantId;
    private String plantName;
    private String plantHeight;
    private String plantPrice;
    private String plantVarient;
    private String plantAvailableQty;
    private String plantRegisteredTime;

}
