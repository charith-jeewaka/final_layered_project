package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.SentEmailBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.SentEmailDao;
import lk.ijse.florist_pos.final_project.Dao.DaoFactory;
import lk.ijse.florist_pos.final_project.Entity.SentEmail;
import lk.ijse.florist_pos.final_project.dto.SentEmailDto;
import java.sql.SQLException;
import java.util.ArrayList;

public class SentEmailBOImpl implements SentEmailBO {

    SentEmailDao sentEmailDao = (SentEmailDao)
            DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.SENT_EMAIL);

    @Override
    public ArrayList<SentEmailDto> getAllSentEmails() throws SQLException {
        ArrayList<SentEmail> sentEmails = sentEmailDao.getAll();
        ArrayList<SentEmailDto> sentEmailDtos = new ArrayList<>();
        for (SentEmail sentEmail:sentEmails){
            sentEmailDtos.add(new SentEmailDto(
                    sentEmail.getRecipientEmail(),
                    sentEmail.getSubject(),
                    sentEmail.getBody(),
                    sentEmail.getTimeStamp()
            )
            );
        }
        return sentEmailDtos;
    }

    @Override
    public boolean saveSentEmails(SentEmailDto sentEmailDto) throws SQLException {
        return sentEmailDao.save(
                new SentEmail(
                        sentEmailDto.getRecipientEmail(),
                        sentEmailDto.getSubject(),
                        sentEmailDto.getBody(),
                        sentEmailDto.getTimeStamp()
                        ));
    }

    @Override
    public boolean deleteSentEmails(String id) throws SQLException {
        return sentEmailDao.delete(id);
    }

}
