package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value="/signup.do")
public class SignupController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        if(req.getQueryString()!=null&&req.getQueryString().equals("error=duplicated_id")){
            req.setAttribute("error_message",  "아이디가 중복되었습니다.");
        }
        return "shop/login/signup_form";
    }
}
