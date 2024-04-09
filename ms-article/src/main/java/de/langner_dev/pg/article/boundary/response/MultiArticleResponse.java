package de.langner_dev.pg.article.boundary.response;

import lombok.Value;

import java.util.List;

@Value
public class MultiArticleResponse {

    ArticleResponse[] articles;

}
