package io.tcooper.data;

import com.mongodb.Block;
import com.mongodb.Function;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import java.util.Collection;
import java.util.List;

public class MongoIterableHelper<T> implements MongoIterable<T> {

  private final List<T> content;

  public MongoIterableHelper(List<T> content) {
    this.content = content;
  }

  public MongoIterableHelper(T content) {
    this.content = List.of(content);
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
    return content == null
        ? null
        : content.stream().findFirst().get();
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
    if(content != null)
      collection.addAll(content);
    return collection;
  }

  @Override
  public MongoIterable batchSize(int i) {
    return null;
  }
}
