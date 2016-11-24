package com.seb.blog.navigation;

public enum Section {
    HOME("Home"),
    POST("Post"),
    CATEGORY("Category"),
    SIGNUP("Sign up"),
    SIGNIN("Sign in"),
    SIGNOUT("Sign out");


    private String value;

    Section(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
