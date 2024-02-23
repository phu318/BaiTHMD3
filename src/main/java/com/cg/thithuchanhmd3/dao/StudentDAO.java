package com.cg.thithuchanhmd3.dao;

import com.cg.thithuchanhmd3.model.Classz;
import com.cg.thithuchanhmd3.model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlyhocvien?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
// search e bỏ bên sevlet
    private  static final String SEARCH = "SELECT * FROM hocvien WHERE name LIKE ?";
    public static final String SELECT_STUDENT_BY_ID = "select hocvien.id, hocvien.`name`, hocvien.email,hocvien.address,hocvien.phone,hocvien.dateofbirth,hocvien.class_id,lop.`classname` as TenLop from hocvien join lop\n" +
            " on `hocvien`.class_id = lop.id where hocvien.id=? ;";
    private static final String INSERT_STUDENT_SQL = "INSERT INTO hocvien (name, email,dateofbirth, address, phone,class_id) VALUES (?, ?, ?,?,?,?);";
    private static final String UPDATE_STUDENT_SQL = "update hocvien set name = ?, email= ?,dateofbirth= ?, address= ?, phone=?,class_id=? where id = ?;";
    private static final String DELETE_STUDENT_SQL = "delete from hocvien where id = ?;";
    private static final String SELECT_ALL_STUDENT_TYPE = "SELECT hocvien.id, \n" +
            "       hocvien.`name`, \n" +
            "       hocvien.email, \n" +
            "       hocvien.address, \n" +
            "       hocvien.phone, \n" +
            "       hocvien.dateofbirth, \n" +
            "       hocvien.class_id, \n" +
            "       lop.classname AS classname \n" +
            "FROM hocvien \n" +
            "JOIN lop \n" +
            "ON hocvien.class_id = lop.id;;";

    @Override
    public Student selectStudentById(int id) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Classz classz=new Classz(
                        rs.getInt("id"),
                        rs.getString("name"));

                student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dateofbirth").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        classz
                );
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List<Student> selectAllStudent() {
        List<Student> student=new ArrayList<>();
        try {
            Connection connection= getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_STUDENT_TYPE);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){
                Classz aClass=new Classz(rs.getInt("id"),rs.getString("classname"));
                        student.add(new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDate("dateofbirth").toLocalDate(),
                                rs.getString("email"),
                                rs.getString("address"),
                                rs.getString("phone"),
                                aClass
                        ));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public void insertStudent(Student student) {
        System.out.println(INSERT_STUDENT_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)
        ) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            LocalDate localDateOfBirth = student.getDateofbirth();
            java.sql.Date sqlDateOfBirth = java.sql.Date.valueOf(localDateOfBirth);
            preparedStatement.setDate(3, sqlDateOfBirth);

            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhone());

            preparedStatement.setInt(6, student.getAclass().getIdclass());


            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteStudent(int id) {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateStudent(Student student) {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            LocalDate localDateOfBirth = student.getDateofbirth();
            java.sql.Date sqlDateOfBirth = java.sql.Date.valueOf(localDateOfBirth);
            preparedStatement.setDate(3, sqlDateOfBirth);

            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhone());

            preparedStatement.setInt(6, student.getAclass().getIdclass());
            preparedStatement.setInt(7, student.getId());


            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

    @Override
    public List<Student> search(String searchName) {
        List<Student> student =new ArrayList<>();
        try (Connection connection = getConnection()) {
            String sql = "SELECT hocvien.id, \n" +
                    "       hocvien.`name`, \n" +
                    "       hocvien.email, \n" +
                    "       hocvien.address, \n" +
                    "       hocvien.phone, \n" +
                    "       hocvien.dateofbirth, \n" +
                    "       hocvien.class_id AS idclass, \n" +
                    "       lop.`classname` AS classname  \n" +
                    "FROM hocvien \n" +
                    "JOIN lop \n" +
                    "ON hocvien.class_id = lop.id \n" +
                    "WHERE hocvien.`name` LIKE ?;";


            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + searchName + "%");
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {


                    Classz classz=new Classz(rs.getInt("idclass"),rs.getString("classname"));

                    student.add(new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDate("dateofbirth").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            classz
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
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
