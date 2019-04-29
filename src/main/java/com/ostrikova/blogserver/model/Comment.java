package com.ostrikova.blogserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity{

    @Column(name = "message")
    private String message;

    @Column(name = "author_id")
    private long authorId;

    @Column(name = "article_id")
    private long articleId;

    public static class Builder {
        private String message;
        private long authorId;
        private long articleId;

        public Builder message(String value) {
            message = value;
            return this;
        }

        public Builder authorId(long value) {
            authorId = value;
            return this;
        }

        public Builder articleId(long value) {
            articleId = value;
            return this;
        }

        public Comment Build() {
            return new Comment(this);
        }
    }

    public Comment() {}

    private Comment(Builder builder) {
        this.message = builder.message;
        this.articleId = builder.articleId;
        this.authorId = builder.authorId;
        this.setCreatedDate(new Date());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
