package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.EnrollmentDto;
import org.example.entity.Enrollment;

import java.sql.SQLException;
import java.util.List;

public interface EnrollmentBO extends SuperBO {
    List<EnrollmentDto> getAllEnrollment() throws SQLException, ClassNotFoundException;

    boolean EnrollmentIdExists(String id) throws SQLException, ClassNotFoundException;

    boolean isStudentEnrolledInCourse(String studentId, String courseId) throws Exception;

    boolean saveEnrollment(EnrollmentDto enrollmentDto) throws Exception;

    String generateNewEnrollmentID() throws Exception;

    List<String> getAllEnrollmentIds() throws SQLException, ClassNotFoundException;

    Enrollment findEnrollmentById(String eid);

    double getRemainingFeeByEnrollmentId(String eid);

    boolean updateRemainingFee(String eid, double updatedRemainFee);

    boolean deleteEnrollment(String id) throws SQLException, ClassNotFoundException;

    boolean updateEnrollment(EnrollmentDto enrollmentDto) throws Exception;
}
