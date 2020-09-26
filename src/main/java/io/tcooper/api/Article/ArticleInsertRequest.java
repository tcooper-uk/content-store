package io.tcooper.api.Article;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ArticleInsertRequest {

  @NotEmpty
  private String name;

  @NotEmpty
  private String description;

  @NotEmpty
  private String content;

  @Min(1)
  private int page;

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
