package com.seb.blog.navigation;

public enum Section {
    HOME("Home"),
    POST("Post"),
    CATEGORY("Category"),
    LOGIN("Log in"),
    SIGNUP("Sign up");


    private String value;

    Section(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
