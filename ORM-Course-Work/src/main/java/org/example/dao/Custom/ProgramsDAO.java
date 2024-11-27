package org.example.dao.Custom;

import org.example.dao.CrudDAO;
import org.example.entity.Programs;

import java.sql.SQLException;

public interface ProgramsDAO extends CrudDAO<Programs> {
    Programs getProgramById(String courseId)throws SQLException, ClassNotFoundException;

    Programs findCourseById(String courseId);

    int getCourseCount() throws SQLException;
}
