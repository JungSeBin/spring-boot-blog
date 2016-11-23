package com.seb.blog.data.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime regDate;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Post> posts;

    public Category() {}
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
