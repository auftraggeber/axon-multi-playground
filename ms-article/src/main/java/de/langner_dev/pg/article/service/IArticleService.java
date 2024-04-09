package de.langner_dev.pg.article.service;

import de.langner_dev.pg.article.command.ArticleAggregate;
import de.langner_dev.pg.article.query.ArticleQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IArticleService {

    CompletableFuture<String> createArticle(String name);
    CompletableFuture<ArticleQuery> findArticle(String articleId);
    CompletableFuture<ArticleQuery[]> findAllArticles();

}
