package io.tcooper.core.mappers;

import io.tcooper.api.Article.ArticleInsertResponse;
import io.tcooper.core.Article;
import java.time.ZonedDateTime;

public class ArticleInsertResponseMapper {

  public static ArticleInsertResponse map(Article article, boolean created) {
    return new ArticleInsertResponse(article, created, ZonedDateTime.now());
  }
}
