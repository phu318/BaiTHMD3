package com.cg.thithuchanhmd3.model;

public class Classz {
    private int idclass;
    private String classname;

    public Classz() {
    }

    public Classz(int idclass, String classname) {
        this.idclass = idclass;
        this.classname = classname;
    }

    public int getIdclass() {
        return idclass;
    }

    public void setIdclass(int idclass) {
        this.idclass = idclass;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
