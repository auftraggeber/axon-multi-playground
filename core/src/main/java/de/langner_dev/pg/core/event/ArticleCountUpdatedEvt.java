package de.langner_dev.pg.core.event;

import lombok.Value;

@Value
public class ArticleCountUpdatedEvt {

    String articleId;

    long count;
}
