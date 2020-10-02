package io.tcooper.api.Article;

import java.util.List;

public class ArticleCollection {

  private final int count;
  private final List<ArticleResponse> data;

  public ArticleCollection(int count, List<ArticleResponse> data) {
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
