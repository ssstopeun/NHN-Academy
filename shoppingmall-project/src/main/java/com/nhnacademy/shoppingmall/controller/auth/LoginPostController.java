package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.data.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.data.user.service.UserService;
import com.nhnacademy.shoppingmall.data.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");

        try{
            userService.doLogin(userId,userPassword);
            HttpSession httpSession = req.getSession();
            httpSession.setMaxInactiveInterval(60*60);
            httpSession.setAttribute("user_id",userId);
            httpSession.setAttribute("user",userService.getUser(userId).get());

        } catch (Exception e) {
            log.info("fail login");
            return "redirect:/login.do?error=login_fail";
        }

        return "redirect:/index.do";
    }
}
