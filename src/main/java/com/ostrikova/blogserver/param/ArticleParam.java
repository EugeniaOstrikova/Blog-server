package com.ostrikova.blogserver.param;

import com.ostrikova.blogserver.model.Tag;

import java.util.List;

public class ArticleParam {
    private String title;

    private String text;

    private long userId;

    public List<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<String> getTags() {
        return tags;
    }
}
