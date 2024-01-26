<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>student - list</title>
    <style>
        table {
            width: 800px;
            border-collapse: collapse;
            border:1px #ccc solid;
        }
        table tr>td,th{
            padding:5px;
            border:1px #ccc solid;
        }
    </style>
    <link rel="stylesheet" href="/style.css" />
</head>

<body>
<h1>학생 리스트</h1>
<p><a href="/student/register" >학생(등록)</a></p>
<table>
    <thead>
    <tr>
        <th style="width: 30%">아이디</th>
        <th style="width: 31%">이름</th>
        <th style="width: 13%">성별</th>
        <th style="width: 13%">나이</th>
        <th style="width: 13%">cmd</th>
    </tr>
    </thead>
    <tbody>
    <!--todo list 구현하기 c:foreach -->
    <c:forEach var="item" items="${studentList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.gender}</td>
            <td>${item.age}</td>
            <td>
                <c:url var="view_link" value="/student/view" scope="request">
                    <c:param name="id" value="${item.id}" />
                </c:url>
                <a href="${view_link}">조회</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>