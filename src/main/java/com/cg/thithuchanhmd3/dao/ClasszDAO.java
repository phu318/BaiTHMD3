package com.cg.thithuchanhmd3.dao;

import com.cg.thithuchanhmd3.model.Classz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasszDAO implements iClasszDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlyhocvien?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final String SELECT_ALL_CLASSZ = "SELECT * FROM lop;";
    @Override
    public List<Classz> selectAllClassz() {
        List<Classz> classz=new ArrayList<>();
        try {
            Connection connection= getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_CLASSZ);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){
                classz.add(new Classz(
                        rs.getInt("id"),
                        rs.getString("classname"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return classz;
    }
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
