package de.langner_dev.pg.article.boundary;

import de.langner_dev.pg.article.boundary.response.ArticleResponse;
import de.langner_dev.pg.article.boundary.response.MultiArticleResponse;
import de.langner_dev.pg.article.query.ArticleQuery;
import de.langner_dev.pg.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final IArticleService articleService;

    @Autowired
    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/create/{name}")
    public ResponseEntity<ArticleResponse> createArticle(@PathVariable("name") String name) throws ExecutionException,
            InterruptedException {
        String id = articleService.createArticle(name).get();
        ArticleQuery query = articleService.findArticle(id).get();
        return ResponseEntity.ok(new ArticleResponse(query.getName(), query.getCount()));
    }

    @RequestMapping
    public ResponseEntity<MultiArticleResponse> getAllArticles() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(
                new MultiArticleResponse(
                        Arrays.stream(articleService.findAllArticles().get())
                                .map(articleQuery -> new ArticleResponse(articleQuery.getName(), articleQuery.getCount()))
                                .toArray(ArticleResponse[]::new)
                )
        );
    }

}
