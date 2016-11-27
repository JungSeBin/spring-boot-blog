package com.seb.blog.controller;

import com.seb.blog.data.entity.Comment;
import com.seb.blog.data.entity.Post;
import com.seb.blog.navigation.Navigation;
import com.seb.blog.navigation.Section;
import com.seb.blog.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
@Navigation(Section.POST)
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final CommentService commentService;
    private final SessionService sessionService;
    private final UserSerivce userSerivce;

    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id, Model model,
                          @ModelAttribute Comment comment) {
        Post post = postService.findOne(id);

        if (post != null) {
            model.addAttribute("post", post);
            model.addAttribute("comment", comment);
        }
        return "post/post";
    }

    @GetMapping("/new")
    public String newPost(HttpSession session, Post post, Model model) {
        if (!sessionService.isAdmin(session.getId())) {
            return "redirect:/";
        }
        model.addAttribute("categories", categoryService.findAll());
        return "post/new";
    }

    @PostMapping
    public String createPost(HttpSession session, @ModelAttribute @Valid Post newPost, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "post/new";
        }
        String userId = sessionService.getUserId(session.getId());
        postService.createPost(userSerivce.findOne(userId), newPost);

        model.addAttribute("post", newPost);
        return "redirect:/posts/" + newPost.getId();
    }

    @GetMapping("/edit/{id}")
    public String editPost(HttpSession session, @PathVariable Long id, Model model) {
        if (!sessionService.isAdmin(session.getId())) {
            return "redirect:/";
        }
        Post post = postService.findOne(id);

        if (post != null) {
            model.addAttribute("editPost", post);
        }
        return "post/edit";
    }

    @PostMapping("/{id}/edit")
    public String modifyPost(@PathVariable Long id, @ModelAttribute("editPost") @Valid Post newPost, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/edit";
        }
        postService.updatePost(id, newPost);
        return "redirect:/posts/" + id;
    }

    @PostMapping("{id}/delete")
    public String deletePost(@PathVariable Long id) {
        commentService.deleteByPost(id);
        postService.deletePost(id);
        return "redirect:/#/";
    }
}
