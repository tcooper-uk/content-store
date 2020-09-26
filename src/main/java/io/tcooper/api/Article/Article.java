package io.tcooper.api.Article;

public class Article {

  private final ArticleUid articleUid;
  private final String name;
  private final String description;
  private final String content;
  private final int page;

  public Article(ArticleUid articleUid, String name, String description, String content, int page) {
    this.articleUid = articleUid;
    this.name = name;
    this.description = description;
    this.content = content;
    this.page = page;
  }

  public ArticleUid getArticleUid() {
    return articleUid;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getContent() {
    return content;
  }

  public int getPage() {
    return page;
  }
}
