package org.example.dao.Custom;

import org.example.dao.CrudDAO;
import org.example.entity.Enrollment;

public interface EnrollmentDAO extends CrudDAO<Enrollment> {
    public boolean isStudentEnrolledInCourse(String studentId, String courseId) throws Exception;

    String generateNewID()throws Exception;

    Enrollment findEnrollmentById(String enrollmentId);

    double getRemainingFeeByEnrollmentId(String enrollmentId);

    boolean updateRemainingFee(String enrollmentId, double newFee);
}


