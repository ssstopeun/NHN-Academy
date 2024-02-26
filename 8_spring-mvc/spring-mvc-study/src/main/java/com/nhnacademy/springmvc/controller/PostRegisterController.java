package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Post;
import com.nhnacademy.springmvc.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Text;

@Controller
@RequestMapping("/post/register")
public class PostRegisterController {
    private final PostRepository postRepository;

    public PostRegisterController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String userRegisterForm() {
        return "postRegister";
    }

    // TODO #1: 게시물 등록 처리 구현
    @PostMapping
    public ModelAndView registerPost(@RequestParam("title") String name,
                                     @RequestParam("content") String text) {
        Post post = postRepository.register(name,text);
        ModelAndView modelAndView = new ModelAndView("postView");
        modelAndView.addObject("post",post);
        return modelAndView;
    }

}
