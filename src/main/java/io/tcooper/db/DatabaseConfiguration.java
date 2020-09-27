package io.tcooper.db;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DatabaseConfiguration {
  private final String name;
  private final String uri;
  private final String username;
  private final String password;

  @JsonCreator
  public DatabaseConfiguration(
      @JsonProperty("name")
      String name,
      @JsonProperty("uri")
      String uri,
      @JsonProperty("user")
          String user,
      @JsonProperty("pass")
          String pass) {
    this.name = name;
    this.uri = uri;
    this.username = user;
    this.password = pass;
  }

  public String getUri() {
    return uri;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }
}
