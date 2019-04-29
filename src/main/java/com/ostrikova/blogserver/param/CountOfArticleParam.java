package com.ostrikova.blogserver.param;

public class CountOfArticleParam {
    private String tag;
    private long articleCount;

    public CountOfArticleParam(String tag, long articleCount){
        this.tag = tag;
        this.articleCount = articleCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(long articleCount) {
        this.articleCount = articleCount;
    }
}
