package io.tcooper.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.client.MongoCollection;
import io.tcooper.core.Article;

public class PersistenceHealthCheck extends HealthCheck {

  private final MongoCollection<Article> mongoCollection;

  public PersistenceHealthCheck(
      MongoCollection<Article> mongoCollection) {
    this.mongoCollection = mongoCollection;
  }

  @Override
  protected Result check() throws Exception {
    try {
      // Try to communicate with our data store.
      mongoCollection.find().limit(1).first();

      return Result.healthy();
    } catch (Exception e) {
      return Result.unhealthy("Error communicating with database.", e);
    }
  }
}
