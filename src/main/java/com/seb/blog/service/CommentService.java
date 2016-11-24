package com.seb.blog.service;

import com.seb.blog.data.dao.CommentDao;
import com.seb.blog.data.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao commentDao;

    public Comment create(Comment comment) {
        comment.setRegDate(LocalDateTime.now());
        return commentDao.save(comment);
    }

    public void delete(Long id) {
        commentDao.delete(id);
    }

    public void deleteByPost(Long postId) {
        List<Comment> comments = commentDao.findAll();

        for(Comment comment : comments) {
            if(comment.getPost().getId() == postId) {
                commentDao.delete(comment);
            }
        }
    }
}
