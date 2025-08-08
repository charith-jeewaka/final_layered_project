package lk.ijse.florist_pos.final_project.Dao.Custom.Impl;

import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.Dao.Custom.SupplierDao;
import lk.ijse.florist_pos.final_project.Entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {

    public  List<String> getAllSupplierEmails() throws SQLException {
        String sql = "SELECT supplier_e_mail FROM supplier";
        List<String> emails = new ArrayList<>();

        Connection con = DBConnection.getInstance().getConnection();
        try (PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                emails.add(rs.getString("supplier_e_mail"));
            }
        }

        return emails;
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Supplier entity) throws SQLException {
        return false;
    }

    @Override
    public Supplier search(String number) throws SQLException {
        return null;
    }
}
