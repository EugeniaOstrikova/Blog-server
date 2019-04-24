package com.ostrikova.blogserver.repository;

import com.ostrikova.blogserver.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, String> {
    Article findById(long id);
}
