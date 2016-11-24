package com.seb.blog.controller;

import com.seb.blog.data.entity.Category;
import com.seb.blog.navigation.Navigation;
import com.seb.blog.navigation.Section;
import com.seb.blog.service.CategoryService;
import com.seb.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
@Navigation(Section.CATEGORY)
public class CategoryController {
    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping
    public String categories(Pageable pageable, Model model) {
        model.addAttribute("categories", categoryService.findAll(pageable));
        return "category/list";
    }

    @GetMapping("/new")
    public String newCategory(@ModelAttribute Category category) {
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
    public String edit(@PathVariable Long id, Model model) {
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
        postService.deleteCategory(id);
        categoryService.delete(id);
        return "redirect:/categories";
    }


}
