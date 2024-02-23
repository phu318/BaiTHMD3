package com.cg.thithuchanhmd3.controller;

import com.cg.thithuchanhmd3.dao.ClasszDAO;
import com.cg.thithuchanhmd3.dao.StudentDAO;
import com.cg.thithuchanhmd3.model.Classz;
import com.cg.thithuchanhmd3.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "staffServlets", value = "/student-page")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;
    private ClasszDAO classzDAO;
    public void init() {
        studentDAO=new StudentDAO();
        classzDAO=new ClasszDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showAddForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteStudent(req, resp);
                break;
            case "search":
                search(req,resp);
                break;
            default:
                showStudentPage(req, resp);
                break;
        }

    }
    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("name");
        List<Student> searchResults = performSearch(searchName);
        request.setAttribute("searchName", searchName);
        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("student/search.jsp").forward(request, response);

    }

    private List<Student> performSearch(String searchName) {
       return studentDAO.search(searchName);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);

        List<Student> listStudent = studentDAO.selectAllStudent();
        request.setAttribute("liststudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request, response);
    }
    private void showStudentPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> listStudent = studentDAO.selectAllStudent();
        req.setAttribute("liststudent", listStudent);

        req.getRequestDispatcher("student/list.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingUser = studentDAO.selectStudentById(id);
        request.setAttribute("liststudent", existingUser);

        request.setAttribute("listCLassz", classzDAO.selectAllClassz());
        request.getRequestDispatcher("student/edit.jsp").forward(request,response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listClassz", classzDAO.selectAllClassz());
        request.getRequestDispatcher("student/create.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    insertStudent(request, response);
                    break;
                case "edit":
                    updateStudent(request, response);
                    break;

                case "search":
                    search(request,response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idclass = Integer.parseInt(request.getParameter("classz"));
        Classz classz = new Classz(idclass,"C1023h1");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateofbirth"));

        Student newStudent = new Student(
                request.getParameter("name"),
                dateOfBirth,
                request.getParameter("email"),
                request.getParameter("address"),
                request.getParameter("phone"),
                classz
        );
        // update ma răng ko lấy id hắn về id bị bằng 0 rồi này
        newStudent.setId(Integer.parseInt(request.getParameter("id")));
        studentDAO.updateStudent(newStudent);

        response.sendRedirect("http://localhost:8080/student-page");

    }
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int classId = Integer.parseInt(request.getParameter("classz"));
        Classz classz = new Classz(classId, "C1023h1");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateofbirth"));


        Student newStudent = new Student(
                request.getParameter("name"),
                dateOfBirth,
                request.getParameter("email"),
                request.getParameter("address"),
                request.getParameter("phone"),
                classz
        );
        studentDAO.insertStudent(newStudent);

        response.sendRedirect("http://localhost:8080/student-page");
    }
}
