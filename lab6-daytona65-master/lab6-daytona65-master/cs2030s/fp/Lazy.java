/**
 * Lazy class that does lazy evaluation.
 * CS2030S Lab 6
 * AY21/22 Semester 2
 *
 * @author Nicholas Arlin Halim (B08K)
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

public class Lazy<T> {
  /** The Producer object.*/
  private Producer<? extends T> producer;

  /** The Maybe object.*/
  private Maybe<T> value;

  /**
   * A factory method to construct the Lazy object.
   *
   * @param <T> The type of value of the given v.
   * @param v The given object of type T to initialize the value field.
   * @return The constructed Lazy object.
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(Maybe.<T>some(v));
  }

  /**
   * A factory method to construct the Lazy object.
   *
   * @param <T> The type of value produced by the given Producer.
   * @param s The given Producer to initialize the producer field.
   * @return The constructed Lazy object.
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s);
  }

  /**
   * A private constructor to construct the Lazy object.
   *
   * @param value The given Maybe to initialize the value field.
   */
  private Lazy(Maybe<T> value) {
    this.value = value;
  }

  /**
   * A private constructor to construct the Lazy object.
   *
   * @param producer The given Producer to initialize the producer field.
   */
  private Lazy(Producer<? extends T> producer) {
    this.producer = producer;
    this.value = Maybe.none();
  }

  /**
   * Return the value wrapped in the value field.
   *
   * @return The value wrapped in target's value field.
   */
  public T get() {
    this.value = Maybe.<T>some(value.orElseGet(this.producer));
    return this.value.orElse(null);
  }

  /**
   * Return the String representation of the value wrapped in the value field.
   *
   * @return The String representation of the value wrapped in target's value field.
   */
  @Override
  public String toString() {
    return String.valueOf(value.orElse("?"));
  }

  /**
   * Stores the given Transformer as a Producer for lazy evaluation.
   *
   * @param <U> The type of value returned by the given Transformer.
   * @param trans The Transformer to be evaluated later.
   * @return The Lazy object with the delayed evaluation of trans stored in its producer field.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> trans) {
    return Lazy.<U>of(() -> trans.transform(this.get()));
  }

  /**
   * Stores the given Transformer as a Producer for lazy evaluation.
   *
   * @param <U> The type of value wrapped by the Lazy returned by the given Transformer.
   * @param trans The Transformer to be evaluated later.
   * @return The Lazy object with the delayed evaluation of trans stored in its producer field.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> trans) {
    return Lazy.<U>of(() -> trans.transform(this.get()).get());
  }

  /**
   * Stores the given BooleanCondition as a Producer for lazy evaluation.
   * @param b The BooleanCondition to be evaluated later.
   * @return The Lazy object with the delayed evaluation of b stored in its producer field.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> b) {
    return Lazy.<Boolean>of(() -> b.test(this.get()));
  }

  /**
   * Checks if the given object is equal to the target.
   *
   * @param obj The object to check against the target.
   * @return A boolean to show if obj is equal to the target.
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Lazy<?>)) {
      return false;
    }
    Lazy<?> lazyobj = (Lazy<?>) obj;
    return this.get().equals(lazyobj.get());
  }

  /**
   * Stores the given Combiner as a Producer for lazy evaluation.
   *
   * @param <S> The type of value wrapped by the given lazy.
   * @param <R> The type of value to be returned by the given combinator.
   * @param lazy The Lazy object to be evaluated by the combinator with the target.
   * @param combinator The Combiner object that evaluates 
   *     lazy and target to return a value of type R.
   * @return The Lazy object wrapping value evaluated by combinator.
   */
  public <S, R> Lazy<R> combine(Lazy<S> lazy, 
      Combiner<? super T, ? super S, ? extends R> combinator) {
    return Lazy.<R>of(() -> combinator.combine(this.get(), lazy.get()));
  }
}
