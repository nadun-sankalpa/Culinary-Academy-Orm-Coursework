package org.example.bo.custom.impl;

import org.example.bo.custom.PaymentBO;
import org.example.dao.Custom.EnrollmentDAO;
import org.example.dao.Custom.PaymentDAO;
import org.example.dao.DAOFactory;
import org.example.dto.PaymentDto;
import org.example.entity.Enrollment;
import org.example.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    EnrollmentDAO enrollmentDAO = (EnrollmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ENROLLMENT);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public List<PaymentDto> getAllPayment() throws SQLException, ClassNotFoundException {
        List<Payment> payments = paymentDAO.getAll();
        List<PaymentDto> dtos = new ArrayList<>();
        for (Payment payment : payments) {
            String enrollmentId = payment.getEnrollment() != null ? payment.getEnrollment().getEid() : null;
            dtos.add(new PaymentDto(payment.getId(),enrollmentId,payment.getAmount(),payment.getDate()));
        }
        return dtos;
    }

    @Override
    public String generateNewPaymentID() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewID();
    }

    @Override
    public boolean PaymentIdExists(String PaymentId) throws SQLException, ClassNotFoundException {
        return paymentDAO.IdExists(PaymentId);
    }

    @Override
    public boolean savePayment(PaymentDto paymentDTO) throws SQLException, ClassNotFoundException {
        Enrollment enrollment = enrollmentDAO.findEnrollmentById(paymentDTO.getEid());
        return paymentDAO.save(new Payment(paymentDTO.getId(),enrollment,paymentDTO.getAmount(),paymentDTO.getDate()));
    }
}
