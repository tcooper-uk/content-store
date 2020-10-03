package io.tcooper.api.Article;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleResponse {

  private final String articleId;
  private final String name;
  private final String description;
  private final int page;
  private final String content;


  @JsonCreator
  public ArticleResponse(
      @JsonProperty("articleUid")
      String articleId,
      @JsonProperty("name")
      String name,
      @JsonProperty("description")
      String description,
      @JsonProperty("page")
      int page,
      @JsonProperty("content")
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
