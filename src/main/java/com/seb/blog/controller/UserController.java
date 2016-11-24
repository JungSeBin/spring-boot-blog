package com.seb.blog.controller;

import com.seb.blog.data.entity.User;
import com.seb.blog.service.UserSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserSerivce userService;

    @GetMapping("/new")
    public String newUser(@ModelAttribute User user) {
        return "user/new";
    }

    @PostMapping
    public String createUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/new";
        }
        userService.create(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute User user) {
        return "user/login";
    }

    @PostMapping("/login")
    public String userProcess(HttpSession session, @ModelAttribute @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/login";
        }
        if(userService.isUserInfoCorrect(user)) {
            return "redirect:/";
        }
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
