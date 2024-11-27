package org.example.dao;

import org.example.dao.Custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,STUDENT,PROGRAMS,PAYMENT,ENROLLMENT
    }

    public SuperDao getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAMS:
                return new ProgramsDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case ENROLLMENT:
                return new EnrollmentDAOImpl();
            default:
                return null;
        }
    }
}
