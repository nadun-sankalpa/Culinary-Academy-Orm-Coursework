package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    private String eid;

    @ManyToOne
    @JoinColumn(name = "sid")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Programs programs;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Payment> paymentList = new ArrayList<>();

    private LocalDate date;
    private Double upfrontpayment;
    private Double remainingfee;

    public Enrollment() {
    }

    public Enrollment(String eid, Student student, Programs programs, LocalDate date, Double upfrontpayment, Double remainingfee) {
        this.eid = eid;
        this.student = student;
        this.programs = programs;
        this.date = date;
        this.upfrontpayment = upfrontpayment;
        this.remainingfee = remainingfee;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getUpfrontpayment() {
        return upfrontpayment;
    }

    public void setUpfrontpayment(Double upfrontpayment) {
        this.upfrontpayment = upfrontpayment;
    }

    public Double getRemainingfee() {
        return remainingfee;
    }

    public void setRemainingfee(Double remainingfee) {
        this.remainingfee = remainingfee;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "eid='" + eid + '\'' +
                ", student=" + student +
                ", programs=" + programs +
                ", paymentList=" + paymentList +
                ", date=" + date +
                ", upfrontpayment=" + upfrontpayment +
                ", remainingfee=" + remainingfee +
                '}';
    }
}
