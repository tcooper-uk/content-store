package io.tcooper.core.mappers;

import io.tcooper.api.Article.ArticleInsertRequest;
import io.tcooper.core.Article;
import io.tcooper.core.ArticleUid;

public class ArticleInsertRequestMapper {

  public static Article map(ArticleUid articleUid, ArticleInsertRequest articleInsertRequest) {
    return new Article(articleUid,
        articleInsertRequest.getName(),
        articleInsertRequest.getDescription(),
        articleInsertRequest.getContent(),
        articleInsertRequest.getPage());
  }

  public static Article map(ArticleInsertRequest articleInsertRequest) {
    return map(new ArticleUid(), articleInsertRequest);
  }

}
