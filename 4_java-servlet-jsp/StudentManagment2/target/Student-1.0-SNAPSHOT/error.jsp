<%--
  Created by IntelliJ IDEA.
  User: kimhwajeong
  Date: 2023/04/05
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>Error Page</title>
</head>

<body>
<table>
    <tbody>
    <tr>
        <!-- todo status_code 출력 -->
        <th>status_code</th>
        <td>${statue_code}</td>
    </tr>
    <tr>
        <!-- todo exception_type 출력 -->
        <th>exception_type</th>
        <td>${exception_type}</td>
    </tr>
    <tr>
        <!-- todo message 출력 -->
        <th>message</th>
        <td>${message}</td>
    </tr>
    <tr>
        <!-- todo exception 출력 -->
        <th>exception</th>
        <td>${exception}</td>
    </tr>
    <tr>
        <!-- todo request_uri 출력 -->
        <th>request_uri</th>
        <td>${request_uri}</td>
    </tr>
    </tbody>
</table>
</body>
</html>