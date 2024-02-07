<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/css/style.css">

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/loginAction.do">

            <h1 class="h3 mb-3 fw-normal text-center">로그인</h1>
            <c:if test = "${not empty error_message}">
                <a class = "mb-3 error_message">${error_message}</a>
            </c:if>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control mb-2" id="user_id" placeholder="회원 아이디" required>
                <label for="user_id">아이디</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control mb-3" id="user_password" placeholder="비밀번호" required>
                <label for="user_password">비밀번호</label>
            </div>
            <button class="w-100 btn btn-lg mt-3 signup-form-signup-button" type="submit">로그인</button>


            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>