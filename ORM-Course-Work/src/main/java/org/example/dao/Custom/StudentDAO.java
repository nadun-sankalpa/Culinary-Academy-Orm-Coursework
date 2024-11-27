package org.example.dao.Custom;

import org.example.dao.CrudDAO;
import org.example.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
//    List<Student> getAll() throws SQLException, ClassNotFoundException;
public Student getStudentById(String studentId) throws Exception;

    Student findStudentById(String sid);

    int getStudentCount() throws SQLException;
}
