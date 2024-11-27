package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ProgramsDto;
import org.example.entity.Programs;

import java.sql.SQLException;
import java.util.List;

public interface ProgramsBO extends SuperBO {
    boolean save(ProgramsDto programDto) throws SQLException, ClassNotFoundException;
    boolean update(ProgramsDto programDto) throws SQLException, ClassNotFoundException;
    boolean delete(String id)throws SQLException, ClassNotFoundException;
    Programs search(String programCode) throws SQLException;
    List<ProgramsDto> getAllPrograms() throws SQLException, ClassNotFoundException;

    List<String> getAllProgramIds() throws SQLException, ClassNotFoundException;
    public Programs getProgramById(String courseId) throws Exception;

    int getCourseCount() throws SQLException;

}
