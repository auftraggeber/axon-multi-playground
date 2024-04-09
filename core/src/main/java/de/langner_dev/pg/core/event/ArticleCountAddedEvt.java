package de.langner_dev.pg.core.event;

import lombok.Value;

@Value
public class ArticleCountAddedEvt {

    String articleId;

    long count;
}
