package de.langner_dev.pg.core.event;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ArticleCreatedEvt {

    String articleId;

    String name;
    long count;

}
