package io.tcooper.core.mappers;

import io.tcooper.api.Article.ArticleResponse;
import io.tcooper.core.Article;

public class ArticleApiResponseMapper {

  public static ArticleResponse map(Article a) {
    return new ArticleResponse(
        a.getArticleUid().toString(),
        a.getName(),
        a.getDescription(),
        a.getPage(),
        a.getContent()
    );
  }

}
