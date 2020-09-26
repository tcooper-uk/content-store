package io.tcooper.api.Article;

import java.time.ZonedDateTime;

public class ArticleResponse {

  private final Article article;
  private final boolean created;
  private final ZonedDateTime createdAt;

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
