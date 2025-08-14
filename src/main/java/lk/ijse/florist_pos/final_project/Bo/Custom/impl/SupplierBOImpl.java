package lk.ijse.florist_pos.final_project.Bo.Custom.impl;

import lk.ijse.florist_pos.final_project.Bo.Custom.SupplierBO;
import lk.ijse.florist_pos.final_project.Dao.Custom.SupplierDao;
import lk.ijse.florist_pos.final_project.Dao.DaoFactory;
import java.sql.SQLException;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDao supplierDao = (SupplierDao)
            DaoFactory.getInstance().getDAO(DaoFactory.DaoTypes.SUPPLIER);

    @Override
    public List<String> getAllSupplierEmails() throws SQLException {
        return supplierDao.getAllSupplierEmails();

    }
}
