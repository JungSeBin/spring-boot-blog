package com.seb.blog.controller;

import com.seb.blog.data.entity.Comment;
import com.seb.blog.data.entity.Post;
import com.seb.blog.service.CommentService;
import com.seb.blog.service.PostService;
import com.seb.blog.service.SessionService;
import com.seb.blog.service.UserSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;
    private final UserSerivce userSerivce;
    private final SessionService sessionService;

    @PostMapping
    public String createComment(HttpSession session, @ModelAttribute @Valid Comment comment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "post/post";
        }
        String userId = sessionService.getUserId(session.getId());

        model.addAttribute("comment", commentService.create(
                userSerivce.findOne(userId),
                new Comment(comment.getContent(), new Post(comment.getPost().getId()))
        ));
        return "redirect:/posts/" + comment.getPost().getId();
    }

    @PostMapping("/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.delete(commentId);
        return "redirect:/posts/" + postId;
    }

    @ModelAttribute
    public Post post(@ModelAttribute Comment comment) {
        if (comment == null) return null;
        else if (comment.getPost() == null) return null;
        return postService.findOne(comment.getPost().getId());
    }
}
