package com.cg.thithuchanhmd3.model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate dateofbirth;
    private String email;
    private String address;
    private String phone;
    private Classz aclass;

    public Student() {
    }

    public Classz getAclass() {
        return aclass;
    }

    public void setAclass(Classz aclass) {
        this.aclass = aclass;
    }

    public Student(String name, LocalDate dateofbirth, String email, String address, String phone, Classz aclass) {
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.aclass = aclass;
    }

    public Student(int id, String name, LocalDate dateofbirth, String email, String address, String phone, Classz aclass) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.aclass = aclass;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
