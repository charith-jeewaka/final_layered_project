package lk.ijse.florist_pos.final_project.Dao;

import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.*;

public  class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return (daoFactory == null)? new DaoFactory():daoFactory;
    }

    public enum DaoTypes{
        CUSTOMER,
        FLOWER,
        FLOWER_WASTE,
        ORDER,
        PLANT,
        QUERY,
        SENT_EMAIL,
        STAFF,
        SUPPLIER,
        SYSTEM_USER
    }
    public SuperDao getDAO(DaoTypes daoType) {
        switch(daoType){
            case CUSTOMER:
                return new CustomerDaoImpl();
            case FLOWER:
                return new FlowerDaoImpl();
            default:
                return null;
        }
    }
}
