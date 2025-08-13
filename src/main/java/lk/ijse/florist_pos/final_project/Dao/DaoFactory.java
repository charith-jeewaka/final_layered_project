package lk.ijse.florist_pos.final_project.Dao;

import lk.ijse.florist_pos.final_project.Dao.Custom.Impl.*;

public  class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
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
            case FLOWER_WASTE:
                return new FlowerWasteDaoImpl();
            case ORDER:
                return new OrderDaoImpl();
            case PLANT:
                return new PlantDaoImpl();
            case QUERY:
                return new QueryDaoImpl();
            case SENT_EMAIL:
                return new SentEmailDaoImpl();
            case STAFF:
                return new StaffDaoImpll();
            case SUPPLIER:
                return new SupplierDaoImpl();
            case SYSTEM_USER:
                return new SystemUserDaoImpl();
            default:
                return null;
        }
    }
}
