package com.nhnacademy.homework1.controller;

import com.nhnacademy.homework1.exception.UserNotFoundException;
import com.nhnacademy.homework1.repository.UserRepository;
import com.nhnacademy.homework1.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

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
