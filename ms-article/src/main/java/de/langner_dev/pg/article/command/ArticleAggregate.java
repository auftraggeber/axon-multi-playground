package de.langner_dev.pg.article.command;

import de.langner_dev.pg.core.command.UpdateArticleCountCmd;
import de.langner_dev.pg.core.command.CreateArticleCmd;
import de.langner_dev.pg.core.event.ArticleCountUpdatedEvt;
import de.langner_dev.pg.core.event.ArticleCreatedEvt;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@NoArgsConstructor
@Aggregate
public class ArticleAggregate {

    @AggregateIdentifier
    String articleId;

    String name;
    long count;

    @CommandHandler
    public ArticleAggregate(CreateArticleCmd cmd) {
        AggregateLifecycle.apply(new ArticleCreatedEvt(cmd.getArticleId(), cmd.getName(), cmd.getCount()));
    }

    @CommandHandler
    public void handle(UpdateArticleCountCmd cmd) {
        if (cmd.getCount() <= 0) return;
        AggregateLifecycle.apply(new ArticleCountUpdatedEvt(cmd.getArticleId(), cmd.getCount()));
    }

    @EventSourcingHandler
    public void on(ArticleCreatedEvt evt) {
        this.articleId = evt.getArticleId();
        this.name = evt.getName();
        this.count = evt.getCount();
    }

    @EventSourcingHandler
    public void on(ArticleCountUpdatedEvt evt) {
        this.count = evt.getCount();
    }



}
