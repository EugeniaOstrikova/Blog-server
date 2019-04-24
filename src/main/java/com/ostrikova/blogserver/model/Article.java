package com.ostrikova.blogserver.model;

import com.ostrikova.blogserver.util.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Article extends AbstractEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "status")
    private Status status;

    public static class Builder {
        private String title;
        private String text;
        private long userId;

        public Builder title(String value) {
            title = value;
            return this;
        }

        public Builder text(String value) {
            text = value;
            return this;
        }
        public Builder userId(long value) {
            userId = value;
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }

    private Article(Builder builder) {
        title = builder.title;
        text = builder.text;
        userId = builder.userId;
        status = Status.DRAFT;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getText () {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUseId(long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
