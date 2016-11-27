package com.seb.blog.data.dao;

import com.seb.blog.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, Long> {
}
