package com.seb.blog.data.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EnableAutoConfiguration

public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    @Lob
    @NotNull
    private String content;

    @Lob
    private String code;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    // 생성자 정의
    public Post() {

    }

    public Post(Long id) {
        this.id = id;
    }

    public Post(String title) {
        this.title = title;
    }

    public Post(String title, String content, String code) {
        this.title = title;
        this.content = content;
        this.code = code;
    }

    public Post(Long id, String title, String content, String code) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.code = code;
    }

    public Post(Long id, String title, String content, String code, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.code = code;
        this.user = user;
    }
}
