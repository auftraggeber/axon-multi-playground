package de.langner_dev.pg.article.boundary.response;

import lombok.Value;

@Value
public class ArticleResponse {

    String articleId;
    String name;
    long count;

}
