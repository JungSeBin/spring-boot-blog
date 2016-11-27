package com.seb.blog.data.dao;

import com.seb.blog.data.entity.Post;
import com.seb.blog.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<Post, Long>{
}
