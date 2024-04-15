package de.langner_dev.pg.article.service;

import de.langner_dev.pg.article.query.ArticleQuery;

import java.util.concurrent.CompletableFuture;

public interface IArticleService {

    CompletableFuture<String> createArticle(String name);
    CompletableFuture<ArticleQuery> findArticleByArticleId(String articleId);
    CompletableFuture<ArticleQuery> findArticleByName(String name);
    CompletableFuture<ArticleQuery[]> findAllArticles();
    void updateArticleCount(String articleId, long newCount);

}
