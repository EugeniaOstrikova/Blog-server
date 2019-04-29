package com.ostrikova.blogserver.repository;

import com.ostrikova.blogserver.model.Article;
import com.ostrikova.blogserver.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, String> {
    Tag findByName(String name);
}
