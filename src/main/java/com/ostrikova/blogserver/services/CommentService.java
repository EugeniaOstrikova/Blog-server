package com.ostrikova.blogserver.services;

import com.ostrikova.blogserver.model.Comment;
import com.ostrikova.blogserver.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsOfArticle(long articleId) {
        Iterable<Comment> commentIterable = commentRepository.findAllByArticleId(articleId);
        List<Comment> comments = new ArrayList<>();
        for (Comment comment : commentIterable) {
            comments.add(comment);
        }

        return comments;
    }

    public Comment getComment(long articleId, long commentId) {
        return commentRepository.findByArticleIdAndId(articleId, commentId);
    }

    public void removeComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
