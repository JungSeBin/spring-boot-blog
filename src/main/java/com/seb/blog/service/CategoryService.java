package com.seb.blog.service;


import com.seb.blog.data.dao.CategoryDao;
import com.seb.blog.data.dao.PostDao;
import com.seb.blog.data.entity.Category;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private static final Logger log = LoggerFactory.getLogger(Category.class);
    private final CategoryDao categoryDao;
    private final PostDao postDao;

    public Category create(Category category) {
        category.setRegDate(LocalDateTime.now());
        return categoryDao.save(category);
    }

    public void delete(Long id) {
        categoryDao.delete(id);
    }

    public void update(Category category) {
        Category oldCategory = categoryDao.findOne(category.getId());
        if(oldCategory != null) {
            oldCategory.setName(category.getName());
        }
    }

    @Transactional(readOnly = true)
    public Page<Category> findAll(Pageable pageable) {
        return categoryDao.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public Category findOne(Long id) {
        return categoryDao.findOne(id);
    }
}