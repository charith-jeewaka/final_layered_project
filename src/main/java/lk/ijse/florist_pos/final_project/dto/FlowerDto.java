package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class FlowerDto {
    private String flowerId;
    private String flowerName;
    private String flowerCategory;
    private String flowerPrice;
    private String flowerStatus;
    private String flowerAvailableQty;
    private String flowerEnteredTime;
}
