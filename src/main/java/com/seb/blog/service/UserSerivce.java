package com.seb.blog.service;

import com.seb.blog.data.dao.UserDao;
import com.seb.blog.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSerivce {
    private final UserDao userDao;

    public User create(User user) {
        return userDao.save(user);
    }

    public boolean isUserInfoCorrect(User user) {
        User dbUser = userDao.findOne(user.getId());
        if (dbUser == null) {
            return false;
        }

        if (dbUser.getId() == user.getId() && dbUser.getPassword() == user.getPassword()) {
            dbUser.setLogined(true);
            return true;
        }
        return false;
    }
}
