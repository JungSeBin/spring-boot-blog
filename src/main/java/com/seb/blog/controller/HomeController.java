package com.seb.blog.controller;

import com.seb.blog.data.dao.PostDao;
import com.seb.blog.data.entity.Post;
import com.seb.blog.navigation.Navigation;
import com.seb.blog.navigation.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.data.domain.ExampleMatcher.matching;

@Controller
@RequiredArgsConstructor
@Navigation(Section.HOME)
public class HomeController {
    private final PostDao postDao;

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String q, Model model,
            @PageableDefault(size = 5, sort = "regDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Example<Post> post = Example.of(new Post(q),
                matching()
                        .withMatcher("title", ExampleMatcher.GenericPropertyMatcher::contains));
        model.addAttribute("posts", postDao.findAll(post, pageable));
        return "index";
    }
}
