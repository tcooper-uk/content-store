package io.tcooper.data;

import com.mongodb.Block;
import com.mongodb.CursorType;
import com.mongodb.Function;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Collation;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.bson.conversions.Bson;

public class MongoFindIterableHelper<T> implements FindIterable<T> {

  private final boolean hasData;
  private final T result;

  public MongoFindIterableHelper(T result) {
    this.hasData = result != null;
    this.result = result;
  }

  @Override
  public FindIterable filter(Bson bson) {
    return null;
  }

  @Override
  public FindIterable limit(int i) {
    return null;
  }

  @Override
  public FindIterable skip(int i) {
    return null;
  }

  @Override
  public FindIterable maxTime(long l, TimeUnit timeUnit) {
    return null;
  }

  @Override
  public FindIterable maxAwaitTime(long l, TimeUnit timeUnit) {
    return null;
  }

  @Override
  public FindIterable modifiers(Bson bson) {
    return null;
  }

  @Override
  public FindIterable projection(Bson bson) {
    return null;
  }

  @Override
  public FindIterable sort(Bson bson) {
    return null;
  }

  @Override
  public FindIterable noCursorTimeout(boolean b) {
    return null;
  }

  @Override
  public FindIterable oplogReplay(boolean b) {
    return null;
  }

  @Override
  public FindIterable partial(boolean b) {
    return null;
  }

  @Override
  public FindIterable cursorType(CursorType cursorType) {
    return null;
  }

  @Override
  public FindIterable batchSize(int i) {
    return null;
  }

  @Override
  public FindIterable collation(Collation collation) {
    return null;
  }

  @Override
  public FindIterable comment(String s) {
    return null;
  }

  @Override
  public FindIterable hint(Bson bson) {
    return null;
  }

  @Override
  public FindIterable hintString(String s) {
    return null;
  }

  @Override
  public FindIterable max(Bson bson) {
    return null;
  }

  @Override
  public FindIterable min(Bson bson) {
    return null;
  }

  @Override
  public FindIterable maxScan(long l) {
    return null;
  }

  @Override
  public FindIterable returnKey(boolean b) {
    return null;
  }

  @Override
  public FindIterable showRecordId(boolean b) {
    return null;
  }

  @Override
  public FindIterable snapshot(boolean b) {
    return null;
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
    return null;
  }

  @Override
  public MongoIterable map(Function function) {

    return hasData
        ? new MongoIterableHelper(function.apply(result))
        : new MongoIterableHelper(null);
  }

  @Override
  public void forEach(Block block) {

  }

  @Override
  public Collection into(Collection collection) {
    return null;
  }

}
