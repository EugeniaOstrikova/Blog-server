package com.ostrikova.blogserver.repository;

import com.ostrikova.blogserver.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {
    Iterable<Comment> findAllByArticleId(long articleId);

    Comment findByArticleIdAndId(long articleId, long id);
}
