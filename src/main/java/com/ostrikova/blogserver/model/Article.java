package com.ostrikova.blogserver.model;

import com.ostrikova.blogserver.util.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToMany
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.addArticle(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getArticles().remove(this);
    }

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

    public Article() {}

    private Article(Builder builder) {
        title = builder.title;
        text = builder.text;
        userId = builder.userId;
        status = Status.DRAFT;
        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
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

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate == null ? null : new Date(updatedDate.getTime());
    }
}
