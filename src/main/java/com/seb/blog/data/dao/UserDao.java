package com.seb.blog.data.dao;

import com.seb.blog.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>{

    User findByGithub(String github);
}
