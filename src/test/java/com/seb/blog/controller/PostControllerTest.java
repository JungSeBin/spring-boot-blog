//package com.seb.blog.controller;
//
//import com.seb.blog.data.entity.Post;
//import com.seb.blog.service.PostService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyLong;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(PostController.class)
//public class PostControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private PostService postService;
//
//    @Test
//    public void findOne() throws Exception {
//        given(this.postService.findOne(anyLong())).willReturn(new Post("제목", "내용", "마크다운"));
//        MvcResult mvcResult = this.mvc.perform(get("/posts/{id}",1)).andExpect(status().isOk()).andReturn();
//
//        Post post = (Post)mvcResult.getModelAndView().getModel().get("post");
//        assertThat(post.getTitle()).isEqualTo("제목");
//        assertThat(post.getContent()).isEqualTo("내용");
//        assertThat(post.getCode()).isEqualTo("마크다운");
//    }
//
//    @Test
//    public void newPost() throws Exception {
//        this.mvc.perform(get("/posts/new"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("post/new"))
//                .andReturn();
//    }
//
//    @Test
//    public void editPost() throws Exception {
//        given(this.postService.findOne(anyLong())).willReturn(new Post("제목", "내용", "마크다운"));
//        MvcResult mvcResult = this.mvc.perform(get("/posts/edit/{id}",1)).andExpect(status().isOk()).andReturn();
//
//        Post post = (Post)mvcResult.getModelAndView().getModel().get("editPost");
//        assertThat(post.getTitle()).isEqualTo("제목");
//        assertThat(post.getContent()).isEqualTo("내용");
//        assertThat(post.getCode()).isEqualTo("마크다운");
//    }
//
//
//    @Test
//    public void createPostValid() throws Exception {
//        this.mvc.perform(post("/posts")
//        .param("title", "제목1")
//        .param("code", "마크다운1")).andExpect(view().name("post/new"));
//    }
//
//    @Test
//    public void modifyPost() throws Exception {
//        Post post = new Post(1L, "제목2", "내용2", "마크다운2");
//        given(postService.updatePost(any(), any())).willReturn(post);
//
//        this.mvc.perform(post("/posts/{id}/edit", 1L)
//                .param("title", "제목2")
//                .param("content", "내용2")
//                .param("code", "마크다운2"))
//                .andExpect(status().isFound())
//                .andExpect(header().string(HttpHeaders.LOCATION, "/posts/1"));
//    }
//
//    @Test
//    public void deletePost() throws Exception {
//        doNothing().when(postService).deletePost(anyLong());
//        this.mvc.perform(post("/posts/{id}/delete", 1L))
//                .andExpect(status().isFound())
//                .andExpect(header().string(HttpHeaders.LOCATION, "/#/"));
//    }
//}
