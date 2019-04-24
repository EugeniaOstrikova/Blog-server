package com.ostrikova.blogserver.services;

import com.ostrikova.blogserver.model.Article;
import com.ostrikova.blogserver.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> getArticles() {
        Iterable<Article> articleIterable = articleRepository.findAll();
        List<Article> articles = new ArrayList<>();

        for (Article article : articleIterable) {
            articles.add(article);
        }

        return articles;
    }

    public void removeArticle (long id) {
        Article article = articleRepository.findById(id);
        articleRepository.delete(article);
    }

    public Article findArticle(long id) {
        return articleRepository.findById(id);
    }
}
