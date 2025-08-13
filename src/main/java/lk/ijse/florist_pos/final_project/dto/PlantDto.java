package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PlantDto {
    private String plantId;
    private String plantName;
    private String plantHeight;
    private String plantPrice;
    private String plantVarient;
    private String plantAvailableQty;
    private String plantRegisteredTime;

}
