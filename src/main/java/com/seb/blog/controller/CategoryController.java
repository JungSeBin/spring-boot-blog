package com.seb.blog.controller;

import com.seb.blog.data.entity.Category;
import com.seb.blog.navigation.Navigation;
import com.seb.blog.navigation.Section;
import com.seb.blog.service.CategoryService;
import com.seb.blog.service.PostService;
import com.seb.blog.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
@Navigation(Section.CATEGORY)
public class CategoryController {
    private final CategoryService categoryService;
    private final PostService postService;
    private final SessionService sessionService;

    @GetMapping
    public String categories(HttpSession session, Pageable pageable, Model model) {
        if(!sessionService.isAdmin(session.getId())) {
            return "redirect:/";
        }

        model.addAttribute("categories", categoryService.findAll(pageable));
        return "category/list";
    }

    @GetMapping("/new")
    public String newCategory(HttpSession session, @ModelAttribute Category category) {
        if(!sessionService.isAdmin(session.getId())) {
            return "redirect:/";
        }

        return "category/new";
    }

    @PostMapping
    public String createCategory(@ModelAttribute @Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/new";
        }
        categoryService.create(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String edit(HttpSession session, @PathVariable Long id, Model model) {
        if(!sessionService.isAdmin(session.getId())) {
            return "redirect:/";
        }

        model.addAttribute("category", categoryService.findOne(id));
        return "category/edit";
    }

    @PostMapping("/{id}/edit")
    public String modifyCategory(@PathVariable Long id, @ModelAttribute @Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/edit";
        }
        categoryService.update(category);
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        postService.deletePostsCategory(id);
        categoryService.delete(id);
        return "redirect:/categories";
    }


}
