package com.seb.blog.controller;

import com.seb.blog.data.dao.PostDao;
import com.seb.blog.data.entity.User;
import com.seb.blog.navigation.Navigation;
import com.seb.blog.navigation.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Navigation(Section.HOME)
public class HomeController {
    private final PostDao postDao;

    @GetMapping({"/", "/home"})
    public String index(Model model, Pageable pageable, @AuthenticationPrincipal User user) {
        model.addAttribute("posts", postDao.findAll(pageable));
        return "index";
    }
}
