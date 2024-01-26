package com.nhnacademy.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name="setCookieServlet",urlPatterns = "/set-cookie")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locale = req.getParameter("locale");
        //HTTP 요청에서 locale파라미터 값 가져오기

        if(Objects.isNull(locale)){
            locale="ko";
        }

        Cookie cookie = new Cookie("locale",locale);
        //cookie를 locale값으로 설정
        cookie.setMaxAge(-1);
        //세션이 종료될때 쿠키 유지
        cookie.setPath("/");
        //쿠키의 유효 경로
        resp.addCookie(cookie); //쿠키 응답

        try(PrintWriter out = resp.getWriter()){
            out.println("OK");
        }
    }
}
