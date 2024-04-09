package de.langner_dev.pg.article.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleQueryRepository extends JpaRepository<ArticleQuery, String> {

    ArticleQuery findByArticleId(String articleId);

}