package de.langner_dev.pg.core.event;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ArticleCreatedEvt {

    @TargetAggregateIdentifier
    String name;
    long count;

}
