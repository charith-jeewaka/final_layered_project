package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;

import lk.ijse.florist_pos.final_project.Dao.Custom.SentEmailDao;
import lk.ijse.florist_pos.final_project.Entity.SentEmail;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SentEmailDaoImpl implements SentEmailDao {
    @Override
    public boolean save(SentEmail sentEmail) {
        try {
            return CrudUtil.execute(
                    "INSERT INTO sent_emails (recipient_email, subject, body) VALUES (?, ?, ?)",
                    sentEmail.getRecipientEmail(),
                    sentEmail.getSubject(),
                    sentEmail.getBody()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<SentEmail> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM sent_emails");
        ArrayList<SentEmail> sentEmails = new ArrayList<>();
        while (resultSet.next()) {
            SentEmail sentEmail = new SentEmail(
                    resultSet.getString("recipient_email"),
                    resultSet.getString("subject"),
                    resultSet.getString("body"),
                    resultSet.getString("sent_at")
            );
            sentEmails.add(sentEmail);
        }
        return sentEmails;
    }

    @Override
    public  boolean delete(String timeStamp) throws SQLException {
        return CrudUtil.execute("DELETE FROM sent_emails WHERE sent_at = ?", timeStamp);
    }

    @Override
    public boolean update(SentEmail entity) throws SQLException {
        return false;
    }

    @Override
    public SentEmail search(String number) throws SQLException {
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }
}
