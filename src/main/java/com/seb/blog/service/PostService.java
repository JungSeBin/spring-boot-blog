package com.seb.blog.service;

import com.seb.blog.data.dao.PostDao;
import com.seb.blog.data.entity.Post;
import com.seb.blog.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;

    public Post createPost(User user, Post post) {
        post.setRegDate(LocalDateTime.now());
        post.setUser(user);
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

    public void deletePostsCategory(Long categoryId) {
        List<Post> postList = postDao.findAll();

        for (Post post : postList) {
            if (post.getCategory() != null && post.getCategory().getId() == categoryId) {
                post.setCategory(null);
            }
        }
    }

    public Page<Post> findAll(Pageable pageable) {
        return postDao.findAll(pageable);
    }

    public Post findOne(Long id) {
        return postDao.findOne(id);
    }
}
