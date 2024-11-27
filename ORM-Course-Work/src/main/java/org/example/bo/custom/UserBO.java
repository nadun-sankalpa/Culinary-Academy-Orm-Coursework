package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException, IOException;
    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException, IOException;

    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException, IOException;

    public String generateNewUserID() throws SQLException, ClassNotFoundException, IOException;
}
