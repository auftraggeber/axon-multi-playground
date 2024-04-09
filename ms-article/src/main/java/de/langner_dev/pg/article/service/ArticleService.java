package de.langner_dev.pg.article.service;

import de.langner_dev.pg.article.query.ArticleQuery;
import de.langner_dev.pg.article.query.request.FindArticleQuery;
import de.langner_dev.pg.article.query.request.FindArticlesQuery;
import de.langner_dev.pg.core.command.CreateArticleCmd;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Override
    public CompletableFuture<String> createArticle(String name) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateArticleCmd(id, name, 0));
    }

    @Override
    public CompletableFuture<ArticleQuery> findArticle(String articleId) {
        return queryGateway.query(new FindArticleQuery(articleId), ArticleQuery.class);
    }

    @Override
    public CompletableFuture<ArticleQuery[]> findAllArticles() {
        return queryGateway.query(new FindArticlesQuery(), ArticleQuery[].class);
    }


}
