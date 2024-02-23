
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <style>

        body {
            padding: 20px;
        }

        .search-form {
            text-align: right;
        }

        .table-container {
            background-size:  100%;
            margin-top: 20px;
        }


        .edit-btn, .delete-btn {
            color: #007bff;
            cursor: pointer;
            margin-right: 10px;
        }

        .delete-btn:hover {
            color: #dc3545;
        }
        .edit-btn:hover {
            color: #dc3545;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="text-center">
        <h1>Staff Management</h1>
        <h2>
            <a href="/student-page?action=create" class="btn btn-primary">ADD New Student</a>
        </h2>
    </div>

    <form action="/student-page?action=search" method="post" class="search-form">
        <label for="search">Search by Name:</label>
        <input type="text" id="search" name="name" />
        <input type="submit" value="Search" class="btn btn-primary" />
    </form>

    <div align="center" class="table-container">
        <table class="table table-bordered table-striped">
            <caption><h2>List of Student</h2></caption>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Email</th>
                <th>DateOfBirth</th>
                <th>Address</th>
                <th>PhoneNumber</th>
                <th>CLass</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="student" items="${liststudent}">
                <tr>
                    <td><c:out value="${student.id}" /></td>
                    <td><c:out value="${student.name}" /></td>
                    <td><c:out value="${student.email}" /></td>
                    <td><c:out value="${student.dateofbirth}" /></td>
                    <td><c:out value="${student.address}" /></td>
                    <td><c:out value="${student.phone}" /></td>
                    <td><c:out value="${student.aclass.classname}" /></td>
                    <td>
                        <a href="/student-page?action=edit&id=${student.id}" class="edit-btn">Edit</a>
                        <a href="/student-page?action=delete&id=${student.id}" class="delete-btn" onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>

