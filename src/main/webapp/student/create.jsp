<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Staff Management Application</title>


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <style>

        body {
            padding: 20px;
        }

        .form-container {
            width: 50%;
            margin: 20px auto;
        }

        /* CSS cho nút "Save Data" */
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
        <h1>Staff Management</h1>
        <h2>

            <a href="/student-page" class="btn btn-primary">List All Student</a>
        </h2>
    </div>

    <div align="center" class="form-container">
        <form method="post">
            <table class="table table-bordered">
                <caption>
                    <h2>Add New Student</h2>
                </caption>
                <tr>
                    <th>Student Name:</th>
                    <td>
                        <input type="text" name="name" id="name" class="form-control" />
                    </td>
                </tr>
                <tr>
                    <th>Student Email:</th>
                    <td>
                        <input type="text" name="email" id="email" class="form-control" />
                    </td>
                </tr>
                <tr>
                    <th>Student Address:</th>
                    <td>
                        <input type="text" name="address" id="address" class="form-control" />
                    </td>
                </tr>
                <tr>
                    <th>Student Phone Number:</th>
                    <td>
                        <input type="text" name="phone" id="phone" class="form-control" />
                    </td>
                </tr>
                <tr>
                    <th>Student DateofBirth:</th>
                    <td>
                        <input type="date" name="dateofbirth" id="dateofbirth" class="form-control" />
                    </td>
                </tr>
                <tr>
                    <th>Classz:</th>
                    <td>
                        <select name="classz" id="classz" class="form-control">
                            <c:forEach var="classz" items="${listClassz}">
                                <option value=${classz.idclass}>${classz.classname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">

                        <input type="submit" value="Save Data" class="save-btn" />
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



