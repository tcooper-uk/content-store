package io.tcooper;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.tcooper.db.DatabaseConfiguration;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContentStoreConfiguration extends Configuration {

  @NotNull
  private DatabaseConfiguration database;

  public DatabaseConfiguration getDatabase() {
    return database;
  }

  public void setDatabase(DatabaseConfiguration database) {
    this.database = database;
  }
}
