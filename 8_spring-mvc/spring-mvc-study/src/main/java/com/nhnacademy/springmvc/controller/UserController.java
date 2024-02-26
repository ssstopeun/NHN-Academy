package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.UserNotFoundException;
import com.nhnacademy.springmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

// TODO #1: UserController
//          GET /user/{userId}          : 사용자 정보 조회
//          GET /user/{userId}/modify   : 사용자 정보 수정 form
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public String getUserInfo(@PathVariable("userId") String userId, Model model) {
        User user = userRepository.getUser(userId);
        if (Objects.isNull(user)) {
            model.addAttribute("exception", new UserNotFoundException());
            return "error";
        }

        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/{userId}/modify")
    public String userModifyForm(@PathVariable("userId") String userId, Model model) {
        User user = userRepository.getUser(userId);
        if (Objects.isNull(user)) {
            model.addAttribute("exception", new UserNotFoundException());
            return "error";
        }

        model.addAttribute("user", user);
        return "userModify";
    }

}
