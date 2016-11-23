package com.seb.blog.data.dao;

import com.seb.blog.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {

    @Override
    void delete(Long aLong);
}
