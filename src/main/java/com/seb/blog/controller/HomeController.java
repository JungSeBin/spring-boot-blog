package com.seb.blog.controller;

import com.seb.blog.navigation.Navigation;
import com.seb.blog.navigation.Section;
import com.seb.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Navigation(Section.HOME)
public class HomeController {
    private final PostService postService;

    @GetMapping("/")
    public String home(Model model,
                       @PageableDefault(size = 5, sort = "regDate", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("posts", postService.findAll(pageable));
        return "index";
    }
}
