package com.ostrikova.blogserver.controller;

import com.ostrikova.blogserver.model.Article;
import com.ostrikova.blogserver.model.Comment;
import com.ostrikova.blogserver.model.Tag;
import com.ostrikova.blogserver.param.ArticleParam;
import com.ostrikova.blogserver.param.CommentParam;
import com.ostrikova.blogserver.param.CountOfArticleParam;
import com.ostrikova.blogserver.services.ArticleService;
import com.ostrikova.blogserver.services.CommentService;
import com.ostrikova.blogserver.services.TagService;
import com.ostrikova.blogserver.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> createArticle(@RequestBody ArticleParam request) {
        HttpHeaders headers = new HttpHeaders();
        Article article = new Article
                .Builder()
                .text(request.getText())
                .title(request.getTitle())
                .userId(request.getUserId())
                .build();

        List<String> tagsName = request.getTags();
        if (tagsName.size() > 0) {
            for (String tagName : tagsName) {
                Tag createdTag = tagService.createTag(tagName);
                article.addTag(createdTag);
            }
        }

        Article createdArticle = articleService.saveArticle(article);

        return new ResponseEntity<>(createdArticle, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> updateArticle(@PathVariable("id") String id, @RequestBody ArticleParam request) {
        HttpHeaders headers = new HttpHeaders();
        Article article = articleService.findArticle(Long.parseLong(id));

        if (article == null || article.getUserId() != request.getUserId()){
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        article.setTitle(request.getTitle());
        article.setText(request.getText());
        article.setUpdatedDate(new Date());

        List<String> tagsName = request.getTags();
        if (tagsName.size() > 0) {
            for (String tagName : tagsName) {
                Tag createdTag = tagService.createTag(tagName);
                if (!createdTag.getArticles().contains(article)) {
                    article.addTag(createdTag);
                }
            }
        }

        Article updatedArticle = articleService.saveArticle(article);

        return new ResponseEntity<>(updatedArticle, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Article>> getArticles() {
        HttpHeaders headers = new HttpHeaders();
        List<Article> articles = articleService.getArticles();

        return new ResponseEntity<>(articles, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/public", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> publicArticle(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        Article article = articleService.findArticle(Long.parseLong(id));

        if (article != null) {
            article.setStatus(Status.PUBLIC);
            Article updatedArticle = articleService.saveArticle(article);
            return new ResponseEntity<>(updatedArticle, headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeArticle(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        articleService.removeArticle(Long.parseLong(id));

        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{articleId}/comments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> addComment(@PathVariable("articleId") String articleId, @RequestBody CommentParam request) {
        HttpHeaders headers = new HttpHeaders();
        Comment comment = new Comment
                .Builder()
                .message(request.getMessage())
                .articleId(Long.parseLong(articleId))
                .authorId(request.getAuthorId())
                .Build();
        Comment createdComment = commentService.createComment(comment);
        return new ResponseEntity<>(createdComment, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{articleId}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> getArticlesComments(@PathVariable("articleId") String articleId) {
        HttpHeaders headers = new HttpHeaders();
        List<Comment> comments = commentService.getCommentsOfArticle(Long.parseLong(articleId));
        return new ResponseEntity<>(comments, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{articleId}/comments/{commentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> getComment(@PathVariable("articleId") String articleId, @PathVariable("commentId") String commentId) {
        HttpHeaders headers = new HttpHeaders();
        Comment comment = commentService.getComment(Long.parseLong(articleId), Long.parseLong(commentId));
        if (comment == null) {
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(comment, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{articleId}/comments/{commentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeComment(@PathVariable("articleId") String articleId, @PathVariable("commentId") String commentId) {
        HttpHeaders headers = new HttpHeaders();
        Comment comment = commentService.getComment(Long.parseLong(articleId), Long.parseLong(commentId));
        commentService.removeComment(comment);
        return new ResponseEntity(headers, HttpStatus.OK);
    }

//    @RequestMapping(value = "/{articleId}/tags", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity getTagsOfArticle(@PathVariable("articleId") String articleId) {
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity(headers, HttpStatus.OK);
//    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CountOfArticleParam>> getTagsCloud() {
        HttpHeaders headers = new HttpHeaders();
        List<Tag> tags = tagService.getTags();
        List<CountOfArticleParam> response = new ArrayList<>();

        for (Tag tag : tags) {
            long countOfArticle = tag.getArticles().size();
            CountOfArticleParam countOfArticleParam = new CountOfArticleParam(tag.getName(), countOfArticle);
            response.add(countOfArticleParam);
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
