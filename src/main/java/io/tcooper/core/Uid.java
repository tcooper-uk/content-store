package io.tcooper.core;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.UUID;

public abstract class Uid {

  private final UUID uuid;

  public Uid() {
    uuid = UUID.randomUUID();
  }

  public Uid(UUID uuid) {
    this.uuid = uuid;
  }

  public Uid(String uuid) {
    this.uuid = UUID.fromString(uuid);
  }

  @JsonValue
  @Override
  public String toString() {
    return uuid.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Uid uid = (Uid) o;
    return Objects.equals(uuid, uid.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
}
