/**
 * The Maybe class.
 * CS2030S Lab 7
 * AY21/22 Semester 2
 *
 * @author Nicholas Arlin Halim (B08K)
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

public abstract class Maybe<T> {

  private static class None extends Maybe<Object> {
    private static final Maybe<?> none = new None();

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    protected Object get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof None;
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> b) {
      return none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> trans) {
      return none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> trans) {
      return none();
    }

    @Override
    public Object orElse(Object obj) {
      return obj;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> prod) {
      return prod.produce();
    }

    @Override
    public void consumeWith(Consumer<? super Object> consumer) {
    }
  }

  private static class Some<T> extends Maybe<T> {
    private final T t;

    protected Some(T t) {
      this.t = t;
    }

    @Override
    public String toString() {
      return "[" + t + "]";
    }

    @Override
    protected T get() {
      return t;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Some<?>) {
        Some<?> s = (Some<?>) obj;
        if (this.t == s.t) {
          return true;
        }
        if (this.t == null || s.t == null) {
          return false;
        }
        return this.t.equals(s.t);
      }
      return false;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> b) {
      if (t == null || b.test(t)) {
        return this;
      }
      return none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> trans) throws NullPointerException {
      Some<U> maybesome = new Some<U>(trans.transform(t));
      Maybe<U> maybes = (Maybe<U>) maybesome;
      return maybes;
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans) 
        throws NullPointerException {
      //We can suppress this as we are sure that trans.transform(t) is of type Maybe<U>.
      @SuppressWarnings("unchecked")
      Maybe<U> maybes = (Maybe<U>) trans.transform(t);
      return maybes;
    }

    @Override
    public T orElse(Object a) {
      return get();
    }

    @Override
    public T orElseGet(Producer<? extends T> prod) {
      return get();
    }

    @Override
    public void consumeWith(Consumer<? super T> consumer) {
      consumer.consume(get());
    }
  }

  public static <T> Maybe<T> none() {
    //We can suppress this as we want to return none as type Maybe<T>.
    @SuppressWarnings("unchecked")
    Maybe<T> maybenone = (Maybe<T>) None.none;
    return maybenone;
  }

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  public static <T> Maybe<T> of(T obj) {
    if (obj == null) {
      return Maybe.none();
    }
    return new Some<T>(obj);
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> b);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> trans);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans);

  public abstract T orElse(Object a);

  public abstract T orElseGet(Producer<? extends T> prod);

  public abstract void consumeWith(Consumer<? super T> consumer);
}
