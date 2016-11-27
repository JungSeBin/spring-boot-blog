package com.seb.blog;

import com.seb.blog.listener.SessionListener;
import com.seb.blog.service.SessionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import javax.servlet.http.HttpSessionListener;

@SpringBootApplication
@EnableRedisHttpSession
public class SebBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SebBlogApplication.class, args);
    }

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new SessionListener(new SessionService());
    }

    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }
}
