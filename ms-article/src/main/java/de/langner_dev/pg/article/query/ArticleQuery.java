package de.langner_dev.pg.article.query;

import de.langner_dev.pg.core.persistence.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ArticleQuery extends BaseEntity<Long> {

    private String name;
    private long count;

}
