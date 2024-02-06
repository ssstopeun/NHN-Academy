<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
  <div class="p-2">
    <form method="post" action="/signupAction.do">

      <h1 class="h3 mb-3 fw-normal text-center">회원가입</h1>

      <div class="form-floating">
        <input type="text" name="user_id" class="form-control mb-2" id="user_name" placeholder="이름" required minlength="2">
        <label for="user_id">이름</label>
      </div>

      <div class="form-floating">
        <input type="text" name="user_id" class="form-control mb-2" id="user_id" placeholder="아이디" required maxlength="20">
        <label for="user_id">아이디</label>
      </div>

      <div class="form-floating">
        <input type="password" name="user_password" class="form-control mb-2" id="user_password" placeholder="비밀번호" required minlength="8" maxlength="16">
        <label for="user_password">비밀번호 (8~16자리)</label>
      </div>

      <div class="form-floating">
        <input type="text" name="user_id" class="form-control mb-3" id="user_birth" placeholder="생년월일" required maxlength="8">
        <label for="user_id">생년월일 (ex.20240206)</label>
      </div>


      <button class="w-100 btn btn-lg mt-3 signup-form-signup-button " href="signup.do">회원가입</button>


      <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

    </form>
  </div>
</div>