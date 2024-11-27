package org.example.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T>  extends SuperDao{
    public List<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T entity)  throws SQLException, ClassNotFoundException;
    boolean update(T entity)  throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    T search(String id) ;
    public boolean IdExists(String id) throws SQLException, ClassNotFoundException;
}
