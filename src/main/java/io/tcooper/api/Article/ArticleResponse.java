package io.tcooper.api.Article;

import io.tcooper.core.Article;
import java.time.ZonedDateTime;

public class ArticleResponse {

  private Article article;
  private boolean created;
  private ZonedDateTime createdAt;

  public ArticleResponse() {
  }

  public ArticleResponse(Article article, boolean created, ZonedDateTime createdAt) {
    this.article = article;
    this.created = created;
    this.createdAt = createdAt;
  }

  public Article getArticle() {
    return article;
  }

  public boolean isCreated() {
    return created;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }
}
