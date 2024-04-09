package de.langner_dev.pg.article.query;

import de.langner_dev.pg.article.query.request.FindArticleQuery;
import de.langner_dev.pg.article.query.request.FindArticlesQuery;
import de.langner_dev.pg.core.event.ArticleCreatedEvt;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleProjection {

    private ArticleQueryRepository repository;

    @QueryHandler
    public ArticleQuery handle(FindArticleQuery query) {
        return repository.findByName(query.getArticleId());
    }

    @QueryHandler
    public ArticleQuery[] handle(FindArticlesQuery query) {
        return repository.findAll().toArray(new ArticleQuery[0]);
    }

    @EventHandler
    public void on(ArticleCreatedEvt evt) {
        repository.save(new ArticleQuery(evt.getName(), 0));
    }

}
