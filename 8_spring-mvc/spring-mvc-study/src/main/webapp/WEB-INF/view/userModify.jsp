<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>사용자 수정</title>
</head>
<body>
<form method="post" action="/user/modify">
    아이디: <input type="text" name="id" value="${user.id}" /><br />
    비밀번호: <input type="password" name="password" value="${user.password}" /><br />
    나이: <input type="text" name="age" value="${user.age}" /><br />
    <input type="submit" />
</form>
</body>
</html>
