<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/loginAction.do">

            <h1 class="h3 mb-3 fw-normal text-center">로그인</h1>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control mb-2" id="user_id" placeholder="회원 아이디" required>
                <label for="user_id">아이디</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control mb-3" id="user_password" placeholder="비밀번호" required>
                <label for="user_password">비밀번호</label>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <button class="w-100 btn btn-lg mt-3 login-button" type="submit">로그인</button>
                </div>
                <div class="col-md-6">
                    <button class="w-100 btn btn-lg mt-3 signup-button " href="signup.do">회원가입</button>
                </div>
            </div>


            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>