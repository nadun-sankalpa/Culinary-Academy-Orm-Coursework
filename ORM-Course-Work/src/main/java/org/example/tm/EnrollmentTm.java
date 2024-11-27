package org.example.tm;

import java.time.LocalDate;

public class EnrollmentTm {
    private String eid;
    private String sid;
    private String Studentname;
    private String cid;
    private String Coursename;
    private LocalDate date;
    private Double upfrontpayment;
    private Double remainingfee;

    public EnrollmentTm() {
    }

    public EnrollmentTm(String eid, String sid, String studentname, String cid, String coursename, LocalDate date, Double upfrontpayment, Double remainingfee) {
        this.eid = eid;
        this.sid = sid;
        Studentname = studentname;
        this.cid = cid;
        Coursename = coursename;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStudentname() {
        return Studentname;
    }

    public void setStudentname(String studentname) {
        Studentname = studentname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCoursename() {
        return Coursename;
    }

    public void setCoursename(String coursename) {
        Coursename = coursename;
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
        return "EnrollmentTm{" +
                "eid='" + eid + '\'' +
                ", sid='" + sid + '\'' +
                ", Studentname='" + Studentname + '\'' +
                ", cid='" + cid + '\'' +
                ", Coursename='" + Coursename + '\'' +
                ", date=" + date +
                ", upfrontpayment=" + upfrontpayment +
                ", remainingfee=" + remainingfee +
                '}';
    }
}
