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
        //me method eken object ekk hdnn plwn.
        if (null == factoryConfig){//methn null wenn plwn ek parai
            return factoryConfig=new SessionFactoryConfig();//me wge class ek athule withrai hdnn plwn
        }
        //deveni prt call krnkot null nathi nis kalin hadena reference ekama return krnw
        return factoryConfig;
    }
    public Session getSession(){//pulic krpu nis ona thenka access krnn plwm.hibernet session return krnn hdnne

        return sessionFactory.openSession();//sesstion type object ekk return krnw
    }

}
