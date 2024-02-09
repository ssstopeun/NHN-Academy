package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.data.user.domain.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "LoginCheckFilter",urlPatterns={"/mypage/*","/mypage.do"})
public class LoginCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#10 /mypage/ 하위경로의 접근은 로그인한 사용자만 접근할 수 있습니다.
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if(session==null||user==null){
            res.sendRedirect("/login.do");
        }else{
            chain.doFilter(req,res);
        }
    }
}