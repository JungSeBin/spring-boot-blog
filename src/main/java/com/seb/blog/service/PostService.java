package com.seb.blog.service;

import com.seb.blog.data.dao.PostDao;
import com.seb.blog.data.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;

    public Post createPost(Post post) {
        post.setRegDate(LocalDateTime.now());
        return postDao.save(post);
    }

    public Post updatePost(Long id, Post post) {
        Post oldPost = postDao.findOne(id);
        if (oldPost != null) {
            oldPost.setContent(post.getContent());
            oldPost.setCode(post.getCode());
            oldPost.setTitle(post.getTitle());
        }
        return oldPost;
    }

    public void deletePost(Long id) {
        Post oldPost = postDao.findOne(id);
        if (oldPost != null) {
            postDao.delete(id);
        }
    }

    public Post findOne(Long id) {
        return postDao.findOne(id);
    }

    public void deleteCategory(Long categoryId) {
        List<Post> postList = postDao.findAll();

        for (Post post : postList) {
            if (post.getCategory() != null && post.getCategory().getId() == categoryId) {
                post.setCategory(null);
            }
        }
    }
}
