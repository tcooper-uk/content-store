package io.tcooper.api.Article;

public class ArticleResponse {

  private final String articleId;
  private final String name;
  private final String description;
  private final int page;
  private final String content;

  public ArticleResponse(String articleId, String name, String description, int page,
      String content) {
    this.articleId = articleId;
    this.name = name;
    this.description = description;
    this.page = page;
    this.content = content;
  }

  public String getArticleId() {
    return articleId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getPage() {
    return page;
  }

  public String getContent() {
    return content;
  }
}
