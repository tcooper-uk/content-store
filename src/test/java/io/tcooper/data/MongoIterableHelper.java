package io.tcooper.data;

import com.mongodb.Block;
import com.mongodb.Function;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import java.util.Collection;

public class MongoIterableHelper<T> implements MongoIterable<T> {

  private final T content;

  public MongoIterableHelper(T content) {
    this.content = content;
  }

  @Override
  public MongoCursor iterator() {
    return null;
  }

  @Override
  public MongoCursor cursor() {
    return null;
  }

  @Override
  public T first() {
    return content;
  }

  @Override
  public MongoIterable map(Function function) {
    return null;
  }

  @Override
  public void forEach(Block block) {

  }

  @Override
  public Collection into(Collection collection) {
    return null;
  }

  @Override
  public MongoIterable batchSize(int i) {
    return null;
  }
}
