package io.tcooper.api.Article;

import io.tcooper.core.Uid;
import java.util.UUID;

/**
 * Typed Uid for our articles
 */
public class ArticleUid extends Uid {

  public ArticleUid() {
  }

  public ArticleUid(UUID uuid) {
    super(uuid);
  }

  public ArticleUid(String uuid) {
    super(uuid);
  }
}
