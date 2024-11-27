package org.example.bo.custom.impl;

import org.example.bo.custom.StudentBO;
import org.example.dao.Custom.StudentDAO;
import org.example.dao.Custom.impl.StudentDAOImpl;
import org.example.dao.DAOFactory;
import org.example.dto.StudentDto;
import org.example.dto.UserDto;
import org.example.entity.Student;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
//    StudentDAO studentDAO = new StudentDAOImpl();
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean save(StudentDto studentDto) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(studentDto.getId(), studentDto.getName(),studentDto.getAddress(),studentDto.getContact(),studentDto.getBirthDay(),studentDto.getGender()));
    }

    @Override
    public boolean update(StudentDto studentDto) throws SQLException, ClassNotFoundException{
        return studentDAO.update(new Student(studentDto.getId(), studentDto.getName(), studentDto.getAddress(), studentDto.getContact(), studentDto.getBirthDay(), studentDto.getGender()));
    }

    @Override
    public boolean delete(String id)throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public boolean search(StudentDto studentDto) {
        return false;
    }

    @Override
    public StudentDto searchStudent(String studentId) {
        return null;
    }

    @Override
    public List<StudentDto> getAllStudents() throws SQLException, ClassNotFoundException {
        List<StudentDto> allStudents= new ArrayList<>();
        List<Student> all = studentDAO.getAll();
        for (Student student : all) {
            allStudents.add(new StudentDto(student.getId(), student.getName(), student.getAddress(),student.getContact(), student.getBirthDay(),student.getGender()));
        }
        return allStudents;
    }

    @Override
    public String generateNewId() {
        return null;
    }

    @Override
    public List<String> getAllStudentIds() throws SQLException, ClassNotFoundException {
        List<String> studentIds = new ArrayList<>();
        List<Student> students = studentDAO.getAll();
        for (Student student : students) {
            studentIds.add(student.getId());
        }
        return studentIds;
    }

    @Override
    public Student getStudentById(String studentId) throws Exception {
        return studentDAO.getStudentById(studentId);
    }

    @Override
    public int getStudentCount() throws SQLException {
        return studentDAO.getStudentCount();
    }
}

