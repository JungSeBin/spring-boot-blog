package com.seb.blog.controller;

import com.seb.blog.data.entity.User;
import com.seb.blog.service.SessionService;
import com.seb.blog.service.UserSerivce;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserSerivce userService;
    private final SessionService sessionService;

    @GetMapping("/new")
    public String newUser(HttpSession session, @ModelAttribute User user) {
        if (sessionService.isLogined(session.getId())) {
            return "redirect:/";
        }
        return "user/new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/new";
        }
        logger.info("User: {}", user);
        User createdUser = userService.create(user);
        if (createdUser == null) {
            return "user/new";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(HttpSession session, @ModelAttribute User user) {
        if (sessionService.isLogined(session.getId())) {
            return "redirect:/";
        }
        return "user/login";
    }

    @PostMapping("/login")
    public String userProcess(HttpServletRequest request, @ModelAttribute User user) {
        if (userService.isUserInfoCorrect(user)) {
            request.getSession().setMaxInactiveInterval(600);

            sessionService.putSession(request.getSession().getId(), user.getId());

            request.getSession().setAttribute("isLogined", true);
            request.getSession().setAttribute("userName", userService.findOne(user.getId()));

            if (sessionService.isAdmin(request.getSession().getId())) {
                request.getSession().setAttribute("isAdmin", true);
            } else {
                request.getSession().setAttribute("isAdmin", false);
            }
            return "redirect:/";
        }
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        sessionService.logout(session.getId());
        session.setAttribute("isLogined", false);
        session.setAttribute("isAdmin", false);
        return "redirect:/";
    }
}
