package io.tcooper.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

public class Article {

  private ObjectId id;
  private ArticleUid articleUid;
  private String name;
  private String description;
  private String content;
  private int page;

  public Article() {
  }

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

  @JsonProperty("_id")
  public void setId(ObjectId id) {
    this.id = id;
  }
}
