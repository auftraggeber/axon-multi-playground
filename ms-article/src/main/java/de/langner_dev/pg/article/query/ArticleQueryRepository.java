package de.langner_dev.pg.article.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleQueryRepository extends JpaRepository<ArticleQuery, String> {

    ArticleQuery findByName(String name);
    ArticleQuery findByArticleId(String articleId);

}