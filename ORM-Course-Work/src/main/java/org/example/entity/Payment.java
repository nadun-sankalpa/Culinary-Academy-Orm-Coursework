package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "eid")
    private Enrollment enrollment;

    private Double amount;

    private LocalDate date;

    public Payment() {
    }

    public Payment(String id, Enrollment enrollment, Double amount, LocalDate date) {
        this.id = id;
        this.enrollment = enrollment;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", enrollment=" + enrollment +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
