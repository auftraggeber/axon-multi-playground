package de.langner_dev.pg.core.event;

import lombok.Value;

@Value
public class ArticleCreatedEvt {

    String articleId;

    String name;
    long count;

}
