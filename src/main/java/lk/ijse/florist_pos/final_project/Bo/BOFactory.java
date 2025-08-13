package lk.ijse.florist_pos.final_project.Bo;

import lk.ijse.florist_pos.final_project.Bo.Custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        if(boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BoTypes{
        CUSTOMER,
        FLOWER,
        FLOWER_WASTE,
        ORDER,
        PLANT,
        SENT_EMAIL,
        STAFF,
        SUPPLIER,
        SYSTEM_USER
    }

    public SuperBO getBo(BoTypes boTypes){
        switch(boTypes){
            case CUSTOMER:
                return new CustomerBoImpl();
            case FLOWER:
                return new FlowerBOImpl();
            case FLOWER_WASTE:
                return new FlowerwasteBOImpl();
            case ORDER:
                return new OrderBoImpl();
            case PLANT:
                return new PlantBOImpl();
            case SENT_EMAIL:
                return new SentEmailBOImpl();
            case STAFF:
                return new StaffBoImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SYSTEM_USER:
                return new SystemUserBOImpl();
            default:
                return null;
        }

    }
}
