package lk.ijse.florist_pos.final_project.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SentEmail {
        private String recipientEmail;
        private String subject;
        private String body;
        private String timeStamp;
    }

