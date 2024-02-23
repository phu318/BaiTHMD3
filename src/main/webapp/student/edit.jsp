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

        .form-container {
            width: 100%;
            margin: 20px auto;
        }

        /* CSS cho nút "Save" */
        .save-btn {
            background-color: #28a745;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
        }

        .save-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="text-center">
        <h1>Student Management</h1>
        <h2>
            <!-- Thêm class Bootstrap cho nút -->
            <a href="/student-page" class="btn btn-primary">List All Student</a>
        </h2>
    </div>

    <div align="center" class="form-container">
        <form method="post" action="student-page?action=edit">
            <table class="table table-bordered">
                <caption>
                    <h2>Edit Student</h2>
                </caption>
                <c:if test="${liststudent != null}">
                    <input type="hidden" name="id" value="<c:out value='${liststudent.id}' />"/>
                </c:if>
                <tr>
                    <th>Student Name:</th>
                    <td>
                        <input type="text" name="name" size="45" class="form-control"
                               value="<c:out value='${liststudent.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Student Email:</th>
                    <td>
                        <input type="text" name="email" size="45" class="form-control"
                               value="<c:out value='${liststudent.email}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Student Address:</th>
                    <td>
                        <input type="text" name="address" size="45" class="form-control"
                               value="<c:out value='${liststudent.address}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Student PhoneNumber:</th>
                    <td>
                        <input type="text" name="phone" size="45" class="form-control"
                               value="<c:out value='${liststudent.phone}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Student DateOfBirth</th>
                    <td>
                        <input type="date" name="dateofbirth" size="45" class="form-control"
                               value="<c:out value='${liststudent.dateofbirth}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Class:</th>

                    <td>
                        <select name="classz" id="classz" class="form-control">
                            <c:forEach var="classzItem" items="${listCLassz}">
                                <option value="${classzItem.idclass}"  ${liststudent.aclass.idclass eq classzItem.idclass ? 'selected' : ''}>${classzItem.classname}</option>
                            </c:forEach>
                        </select>
                    </td>

                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <!-- Thêm class CSS tùy chỉnh cho nút -->
                        <input type="submit" value="Save" class="save-btn" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
