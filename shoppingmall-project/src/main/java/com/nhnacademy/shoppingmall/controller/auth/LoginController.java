package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping(method = RequestMapping.Method.GET,value = "/login.do")
public class LoginController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //todo#13-1 session이 존재하고 로그인이 되어 있다면 redirect:/index.do 반환 합니다.
        HttpSession session = req.getSession(false);

        // 세션에서 이전 URL을 읽어옴

        if(session!=null&&session.getAttribute("user_id")!=null){
            return "redirect:/index.do";
        }

        if(req.getQueryString()!=null){
            if(req.getParameter("error")!=null&&req.getParameter("error").equals("login_fail")){
                req.setAttribute("error_message", "아이디 또는 비밀번호가 틀렸습니다.");
            }
            if(req.getParameter("returnUrl")!=null&&req.getParameter("returnUrl").equals("/mypage.do")){
                req.setAttribute("returnUrl","/mypage.do");
            }
        }


        return "shop/login/login_form";
    }
}
