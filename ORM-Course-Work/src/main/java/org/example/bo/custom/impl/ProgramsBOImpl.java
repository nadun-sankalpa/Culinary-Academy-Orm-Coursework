package org.example.bo.custom.impl;

import org.example.bo.custom.ProgramsBO;
import org.example.dao.Custom.ProgramsDAO;
import org.example.dao.DAOFactory;
import org.example.dto.ProgramsDto;
import org.example.entity.Programs;
import org.example.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramsBOImpl implements ProgramsBO {

    ProgramsDAO programsDAO = (ProgramsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAMS);
    @Override
    public boolean save(ProgramsDto programDto) throws SQLException, ClassNotFoundException {
        return programsDAO.save(new Programs(programDto.getId(), programDto.getName(),programDto.getDuration(),programDto.getFee()));
    }

    @Override
    public boolean update(ProgramsDto programDto) throws SQLException, ClassNotFoundException {
        return programsDAO.update(new Programs(programDto.getId(), programDto.getName(), programDto.getDuration(), programDto.getFee()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return programsDAO.delete(id);
    }

    @Override
    public Programs search(String programCode) throws SQLException {
        return null;
    }

    @Override
    public List<ProgramsDto> getAllPrograms() throws SQLException, ClassNotFoundException {
        List<ProgramsDto> allPrograms= new ArrayList<>();
        List<Programs> all = programsDAO.getAll();
        for (Programs programs : all) {
            allPrograms.add(new ProgramsDto(programs.getId(), programs.getName(), programs.getDuration(), programs.getFee()));
        }
        return allPrograms;
    }

    @Override
    public List<String> getAllProgramIds() throws SQLException, ClassNotFoundException {
        List<String> programIds = new ArrayList<>();
        List<Programs> courses = programsDAO.getAll();
        for (Programs programs : courses) {
            programIds.add(programs.getId());
        }
        return programIds;
    }

    @Override
    public Programs getProgramById(String courseId) throws Exception {
        return programsDAO.getProgramById(courseId);
    }

    @Override
    public int getCourseCount() throws SQLException {
        return programsDAO.getCourseCount();
    }
}
