package com.nhnacademy.homework1.controller;

import com.nhnacademy.homework1.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request,
                        Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            return "loginForm";
        } else {
            if(Objects.nonNull(session.getAttribute("id"))){
                model.addAttribute("id",session.getAttribute("id"));
            }
            return "redirect:/";
        }
    }
    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                @RequestParam("pwd") String pwd,
                HttpServletRequest request) {

        if(userRepository.matches(id,pwd)){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("id",id);
            return "redirect:/";
        }else{
            return "redirect:/login";
        }
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
//        Cookie cookie = new Cookie("SESSION", null);
//        cookie.setMaxAge(0);
//        cookie.setPath("/");
//        response.addCookie(cookie);

        return "redirect:/login";
    }
}
