package com.seb.blog.data.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@EnableAutoConfiguration

public class User implements Serializable {
    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String password;

    private String name;

    private String email;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Post> posts = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Comment> comments = new ArrayList<>();

    public User() {
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
