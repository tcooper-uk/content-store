package io.tcooper.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Saying {

  private long id;
  private String content;

  public Saying() {
    // JSON deserialize
  }

  public Saying(long id, String content) {
    this.id = id;
    this.content = content;
  }

  @JsonProperty
  public long getId() {
    return id;
  }

  @JsonProperty
  public String getContent() {
    return content;
  }
}
