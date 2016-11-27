package com.seb.blog.service;

import com.seb.blog.data.dao.UserDao;
import com.seb.blog.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSerivce {
    private static final Logger logger = LoggerFactory.getLogger(UserSerivce.class);
    private final UserDao userDao;

    public User findOne(String userId) {
        User user = userDao.findOne(userId);
        if (user == null) {
            return null;
        }
        return user;
    }

    public User create(User user) {
        if (userDao.findOne(user.getId()) != null) {
            return null;
        }
        userDao.save(user);
        return user;
    }

    public boolean isUserInfoCorrect(User user) {
        User dbUser = userDao.findOne(user.getId());
        if (dbUser == null) {
            return false;
        }

        if (dbUser.getPassword().equals(user.getPassword())) {
            logger.info("Login User: {}", dbUser);
            return true;
        }
        return false;
    }
}
