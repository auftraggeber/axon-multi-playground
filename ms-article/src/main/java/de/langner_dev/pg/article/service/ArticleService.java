package de.langner_dev.pg.article.service;

import de.langner_dev.pg.article.query.ArticleQuery;
import de.langner_dev.pg.article.query.request.FindArticleQueryByArticleId;
import de.langner_dev.pg.article.query.request.FindArticleQueryByName;
import de.langner_dev.pg.article.query.request.FindArticlesQuery;
import de.langner_dev.pg.core.command.CreateArticleCmd;
import de.langner_dev.pg.core.command.UpdateArticleCountCmd;
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
        ArticleQuery articleQuery = queryGateway.query(new FindArticleQueryByName(name), ArticleQuery.class).join();
        if (articleQuery != null) throw new IllegalArgumentException("Article already exists");

        String id = UUID.randomUUID().toString();
        return commandGateway.send(new CreateArticleCmd(id, name, 0));
    }

    @Override
    public CompletableFuture<ArticleQuery> findArticleByArticleId(String articleId) {
        return queryGateway.query(new FindArticleQueryByArticleId(articleId), ArticleQuery.class);
    }


    @Override
    public CompletableFuture<ArticleQuery> findArticleByName(String articleName) {
        return queryGateway.query(new FindArticleQueryByName(articleName), ArticleQuery.class);
    }

    @Override
    public CompletableFuture<ArticleQuery[]> findAllArticles() {
        return queryGateway.query(new FindArticlesQuery(), ArticleQuery[].class);
    }

    @Override
    public void updateArticleCount(String articleId, long newCount) {
        commandGateway.send(new UpdateArticleCountCmd(articleId, newCount));
    }



}
