package org.example.dao.Custom;

import org.example.dao.CrudDAO;
import org.example.entity.User;
import org.hibernate.Session;

import java.io.IOException;

public interface UserDAO extends CrudDAO<User> {
    boolean checkPassword(String username, String password) throws IOException;
}
