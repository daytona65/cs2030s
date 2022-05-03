/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Nicholas Arlin Halim (B08K)
 */

class Box<T> {
  private final T content;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj instanceof Box<?>) {
      Box<?> b = (Box<?>) obj;
      if (this.content == b.content) {
        return true;
      }

      if (this.content == null || b.content == null) {
        return false;
      }
      return this.content.equals(b.content);
    }
    return false;
  }

  @Override
  public String toString() {
    if (content == null) {
      String s = "[]";
      return s;
    }
    String s = "[" + content + "]";
    return s;
  }

  private Box(T content) {
    this.content = content;
  }

  public static <T> Box<T> of(T a) {
    if (a == null) {
      return null;
    }
    return new Box<T>(a);
  }

  public static <T> Box<T> ofNullable(T a) {
    if (a == null) {
      return empty();
    }
    return new Box<T>(a);
  }

  public static <T> Box<T> empty() {
    //The only way we can create an empty box is through the
    //method empty() and we only create a copy of empty box of type T
    //inside without modifying the static final empty box. 
    //So it is safe to cast EMPTY_BOX to Box<T>.
    @SuppressWarnings("unchecked")
    Box<T> tmp = (Box<T>) EMPTY_BOX;
    return tmp;
  }

  public boolean isPresent() {
    return content != null;
  }

  public Box<T> filter(BooleanCondition<? super T> b) {
    if (!isPresent() || !b.test(content)) {
      return empty();
    }
    return this;
  }

  public <U> Box<U> map(Transformer<? super T, U> t) {
    if (!isPresent()) {
      return empty();
    }
    //The only way to transform a Box<T> to Box<U> is through
    //the map() method, and we only put content of type T
    //inside transform() as this method is called within Box<T>.
    //Hence it is safe to cast content to T.
    @SuppressWarnings("unchecked")
    U newcontent = t.transform((T) this.content);
    return new Box<U>(newcontent);
  }
}

















