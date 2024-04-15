package de.langner_dev.pg.article.boundary;

import de.langner_dev.pg.article.boundary.response.ArticleResponse;
import de.langner_dev.pg.article.query.ArticleQuery;
import de.langner_dev.pg.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/{name}/create")
    public ResponseEntity<ArticleResponse> createArticle(@PathVariable("name") String name) throws ExecutionException,
            InterruptedException {
        try {
            String id = articleService.createArticle(name).get();
            ArticleQuery query = articleService.findArticleByArticleId(id).get();
            return ResponseEntity.ok(new ArticleResponse(query.getName(), query.getCount()));
        }
        catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping
    public ResponseEntity<ArticleResponse[]> getAllArticles() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(
                Arrays.stream(articleService.findAllArticles().get())
                        .map(articleQuery -> new ArticleResponse(articleQuery.getName(), articleQuery.getCount()))
                        .toArray(ArticleResponse[]::new)
        );
    }

    @RequestMapping("/{name}")
    public ResponseEntity<ArticleResponse> getArticleByName(@PathVariable("name") String name) {
        try {
            ArticleQuery query = articleService.findArticleByName(name).get();
            return ResponseEntity.ok(new ArticleResponse(query.getName(), query.getCount()));
        }
        catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/{name}/count/add")
    public ResponseEntity<ArticleResponse> addArticleCount(@PathVariable("name") String name, @Param("count") long count) {
        return ResponseEntity.internalServerError().build();
    }

}
