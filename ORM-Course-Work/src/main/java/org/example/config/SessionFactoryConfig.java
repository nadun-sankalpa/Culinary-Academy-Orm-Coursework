package org.example.config;

import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;

    private final SessionFactory sessionFactory;

    private SessionFactoryConfig(){


        sessionFactory= new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Programs.class)
                .addAnnotatedClass(Enrollment.class)
                .addAnnotatedClass(Payment.class)
                .buildSessionFactory();
    }
    public static SessionFactoryConfig getInstance(){

        if (null == factoryConfig){
            return factoryConfig=new SessionFactoryConfig();
        }

        return factoryConfig;
    }
    public Session getSession(){

        return sessionFactory.openSession();
    }

}
