package com.seb.blog.data.dao;

import com.seb.blog.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long>{
}
