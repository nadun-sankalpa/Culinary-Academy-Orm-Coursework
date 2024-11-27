package org.example.dao.Custom.impl;

import org.example.config.SessionFactoryConfig;
import org.example.dao.Custom.ProgramsDAO;
import org.example.entity.Programs;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ProgramsDAOImpl implements ProgramsDAO {
    @Override
    public List<Programs> getAll() throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM programs");
        nativeQuery.addEntity(Programs.class);
        List<Programs> programs = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return programs;
    }

    @Override
    public boolean save(Programs entity) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Programs entity) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DELETE FROM programs WHERE program_id = :id";
        NativeQuery<Programs> nativeQuery = session.createNativeQuery(sql);
        nativeQuery.setParameter("id",id);
        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Programs search(String id) {
        return null;
    }

    @Override
    public boolean IdExists(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Programs getProgramById(String courseId) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        try {
            // Fetch the course object based on the ID
            Programs programs = session.get(Programs.class, courseId);
            tx.commit();  // Commit the transaction
            return programs;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Programs findCourseById(String courseId) {
        Transaction transaction = null;
        Programs programs = null;

        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Programs> query = session.createQuery("FROM Programs p WHERE p.program_id = :id", Programs.class);
            query.setParameter("id", courseId);
            programs = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }

        return programs;
    }

    @Override
    public int getCourseCount() throws SQLException {
        int courseCount = 0;
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(c) FROM Programs c", Long.class);
            Long result = query.uniqueResult();
            if (result != null) {
                courseCount = result.intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to fetch course count from the database", e);
        }
        return courseCount;
    }
}
