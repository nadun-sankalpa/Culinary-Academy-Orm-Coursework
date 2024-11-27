package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.PaymentDto;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    List<PaymentDto> getAllPayment() throws SQLException, ClassNotFoundException;

    String generateNewPaymentID()throws SQLException, ClassNotFoundException;

    boolean PaymentIdExists(String id) throws SQLException, ClassNotFoundException;

    boolean savePayment(PaymentDto paymentDTO) throws SQLException, ClassNotFoundException;
}
