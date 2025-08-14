package lk.ijse.florist_pos.final_project.Bo.Custom;

import lk.ijse.florist_pos.final_project.Bo.SuperBO;
import lk.ijse.florist_pos.final_project.dto.SentEmailDto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SentEmailBO extends SuperBO {

    ArrayList<SentEmailDto> getAllSentEmails() throws SQLException;

    boolean saveSentEmails(SentEmailDto sentEmailDto) throws SQLException, ClassNotFoundException ;

    boolean deleteSentEmails(String id) throws SQLException, ClassNotFoundException ;

}
