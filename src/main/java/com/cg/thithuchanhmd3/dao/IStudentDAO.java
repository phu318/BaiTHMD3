package com.cg.thithuchanhmd3.dao;

import com.cg.thithuchanhmd3.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {
    Student selectStudentById(int id);
    List<Student> selectAllStudent();
    void insertStudent(Student student);

    public boolean deleteStudent(int id) ;

    boolean updateStudent(Student staff);

    List<Student> search(String search);
}
