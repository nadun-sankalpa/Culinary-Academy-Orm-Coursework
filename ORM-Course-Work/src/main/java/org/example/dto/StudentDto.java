package org.example.dto;

import java.time.LocalDate;

public class StudentDto {
    private String id;
    private String name;
    private String address;
    private String contact;
    private LocalDate birthDay;
    private String gender;


    public StudentDto() {
    }

    public StudentDto(String id, String name, String address, String contact, LocalDate birthDay, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    @Override
    public String toString() {
        return "StudentDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", birthDay=" + birthDay +
                ", gender='" + gender + '\''+
                '}';
    }
}
