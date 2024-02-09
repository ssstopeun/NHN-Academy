<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
    <!-- 왼쪽 네비게이션 -->
    <%@ include file="mypage_form.jsp" %>

    <!-- 오른쪽 개인정보 수정 -->
    <div class="col-lg-10">
        <div class="container">
            <h2 class="my-4">개인정보 수정</h2>
            <form action="/myPage/userModifyAction.do" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">이름</label>
                    <input type="text" class="form-control" id="username" name="username" value="현재 사용자의 이름">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요">
                </div>
                <div class="mb-3">
                    <label for="birthdate" class="form-label">생년월일</label>
                    <input type="text" class="form-control" id="birthdate" name="birthdate" value="현재 사용자의 생년월일">
                </div>
                <!-- 기타 필요한 입력 필드들을 추가할 수 있습니다 -->
                <button type="submit" class="btn btn-primary">수정하기</button>
            </form>
        </div>
    </div>
</div>
