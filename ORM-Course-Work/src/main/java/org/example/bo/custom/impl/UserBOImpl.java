package org.example.bo.custom.impl;

import org.example.bo.custom.UserBO;
import org.example.config.SessionFactoryConfig;
import org.example.dao.Custom.UserDAO;
import org.example.dao.Custom.impl.UserDAOImpl;
import org.example.dao.DAOFactory;
import org.example.dto.UserDto;
import org.example.entity.Student;
import org.example.entity.User;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException, IOException {
        List<UserDto> allUsers= new ArrayList<>();
        List<User> all = userDAO.getAll();
        for (User user : all) {
            allUsers.add(new UserDto(user.getUserId(), user.getUserName(), user.getPassword(), user.getRole()));
        }
        return allUsers;
    }

    @Override
    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.save(new User(dto.getUserId(), dto.getUserName(),dto.getPassword(),dto.getRole()));
    }

    @Override
    public boolean updateUser(UserDto userDto) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.update(new User(userDto.getUserId(), userDto.getUserName(), userDto.getPassword(), userDto.getRole()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.delete(id);
    }

    @Override
    public String generateNewUserID() throws SQLException, ClassNotFoundException, IOException {
        return null;
    }
}

