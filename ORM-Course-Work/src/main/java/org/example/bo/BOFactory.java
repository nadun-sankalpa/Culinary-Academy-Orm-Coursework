package org.example.bo;

import org.example.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory bOFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (bOFactory==null)?bOFactory=new BOFactory():bOFactory;
    }

    public enum BOTypes{
        USER,STUDENT,PROGRAMS,PAYMENT,ENROLLMENT
    }

    public SuperBO getBO(BOTypes boTypes){
        switch(boTypes){
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case PROGRAMS:
                return new ProgramsBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case ENROLLMENT:
                return new EnrollmentBOImpl();
            default:
                return null;
        }
    }
}
