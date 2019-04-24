package com.ostrikova.blogserver.controller;

import com.ostrikova.blogserver.model.Article;
import com.ostrikova.blogserver.param.ArticleParam;
import com.ostrikova.blogserver.services.ArticleService;
import com.ostrikova.blogserver.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> createArticle(@RequestBody ArticleParam request) {
        HttpHeaders headers = new HttpHeaders();
        Article article = new Article
                .Builder()
                .text(request.getText())
                .title(request.getTitle())
                .userId(request.getUserId())
                .build();
        Article createdArticle = articleService.createArticle(article);

        return new ResponseEntity<>(createdArticle, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> updateArticle(@PathVariable("id") String id, @RequestBody ArticleParam request) {
        HttpHeaders headers = new HttpHeaders();
        Article article = articleService.findArticle(Long.parseLong(id));

        if (article == null) {
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        article.setTitle(request.getTitle());
        article.setText(request.getText());
        Article updatedArticle = articleService.updateArticle(article);

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
            Article updatedArticle = articleService.updateArticle(article);
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

}
