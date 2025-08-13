package lk.ijse.florist_pos.final_project.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SystemUser {
    private int userId;
    private String userName;
    private String password;
    private String userRole;
    private String userMobile;
    private String userEmail;
    private String userNic;
}
