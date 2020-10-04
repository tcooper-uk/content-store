package io.tcooper.api.Article;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ArticleCollection {

  private final int count;
  private final List<ArticleResponse> data;

  @JsonCreator
  public ArticleCollection(
      @JsonProperty("count")
      int count,
      @JsonProperty("data")
      List<ArticleResponse> data) {
    this.count = count;
    this.data = data;
  }

  public int getCount() {
    return count;
  }

  public List<ArticleResponse> getData() {
    return data;
  }
}
