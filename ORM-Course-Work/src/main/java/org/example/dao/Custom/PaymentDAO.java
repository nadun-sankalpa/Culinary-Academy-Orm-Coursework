package org.example.dao.Custom;

import org.example.dao.CrudDAO;
import org.example.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {
    String generateNewID() throws SQLException, ClassNotFoundException;
}
