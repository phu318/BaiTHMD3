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

        .table-container {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="text-center">
        <h1>Student Management</h1>
        <h2>

            <a href="/student-page" class="btn btn-primary">Student Page</a>
        </h2>
    </div>

    <h1>Search name: <c:out value="${searchName}" /></h1>

    <div align="center" class="table-container">
        <table class="table table-bordered">
            <caption><h2>List of Student</h2></caption>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>PhoneNumber</th>
                <th>DateOfBirth</th>
                <th>Class</th>
            </tr>
            <c:forEach var="student" items="${searchResults}">
                <tr>
                    <td><c:out value="${student.id}" /></td>
                    <td><c:out value="${student.name}" /></td>
                    <td><c:out value="${student.email}" /></td>
                    <td><c:out value="${student.dateofbirth}" /></td>
                    <td><c:out value="${student.address}" /></td>
                    <td><c:out value="${student.phone}" /></td>
                    <td><c:out value="${student.aclass.classname}" /></td>
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

