package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An Infinite List that wraps its values with the Lazy
 * class for lazy evaluation.
 * CS2030S Lab 7
 * AY21/22 Semester 2
 *
 * @author Nicholas Arlin Halim (B08K)
 *
 */
public class InfiniteList<T> {
  /** The wrapped Lazy object around a Maybe object that wraps a value of type T. */
  private final Lazy<Maybe<T>> head;

  /** The wrapped Lazy object around the tail of the InfiniteList. */
  private final Lazy<InfiniteList<T>> tail;

  /**
   * A private constructor to initialize the InfiniteList.
   */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /**
   * Generate the content of the list. Given a producer, lazily generate the list of elements
   * contained in the producer.
   *
   * @param <T> The type of elements in the InfiniteList.
   * @param producer The producer containing the element.
   * @return The created InfiniteList.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<T>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> InfiniteList.generate(producer)));
  }

  /** 
   * Iterate the content of the list. Given a seed and a transformer, lazily iterate through
   * the list of elements by transforming the seed with each iteration.
   *
   * @param <T> The type of elements in the InfiniteList.
   * @param seed The starting seed for the iteration.
   * @param next The transformer that generates the subsequent elements of the InfiniteList
   from the seed.
   * @return The created InfiniteList.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<T>(seed, () -> InfiniteList.iterate(next.transform(seed), next));
  }

  /**
   * A private constructor to initialize the InfiniteList.
   *
   * @param head The value passed into the InfiniteList.
   * @param tail The tail of the InfiniteList, wrapped in a producer.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head)); 
    this.tail = Lazy.of(tail);
  }

  /**
   * A private constructor to initialize the InfiniteList.
   *
   * @param head The wrapped Lazy object containing a Maybe object wrapping a value of type T.
   * @param tail The tail of the InfiniteList, wrapped in a Lazy object.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Returns the element wrapped in the Lazy and Maybe object,
   * in the head of the InfiniteList.
   *
   * @return The element of type T in the head of the InfiniteList.
   */
  public T head() {
    return head.get().orElseGet(() -> tail.get().head());
  }

  /**
   * Returns the tail of the InfiniteList.
   *
   * @return The tail of the InfiniteList.
   */
  public InfiniteList<T> tail() {
    return head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /**
   * Returns an InfiniteList with the elements of the target transformed by mapper
   * stored in Lazy objects for lazy evaluation.
   *
   * @param <R> The type of value returned by the given Transformer.
   * @param mapper The Transformer to be evaluated later.
   * @return The created InfiniteList with the target's elements transformed by mapper.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<R>(Lazy.of(() -> Maybe.some(mapper.transform(this.head()))),
        Lazy.of(() -> this.tail().map(mapper)));
  }

  /**
   * Returns an InfiniteList with elements of the target that satisfy the given
   * BooleanCondition stored in Lazy objects for lazy evaluation.
   *
   * @param predicate The BooleanCondition to be evaluated later.
   * @return The created InfiniteList with the target's elements that satisfy predicate.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<T>(Lazy.of(() -> this.head.get().filter(predicate)),
        Lazy.of(() -> this.tail.get().filter(predicate)));
  }

  /**
   * The Sentinel class to represent the end of an InfiniteList.
   */
  private static class Sentinel extends InfiniteList<Object> {
    /** The Sentinel object to represent the end of an InfiniteList. */
    private static final InfiniteList<Object> SENTINEL = new Sentinel();

    /** 
     * The overridden toString() method to represent a Sentinel.
     *
     * @return The string dash to represent a Sentinel.
     */
    @Override
    public String toString() {
      return "-";
    }
    
    /**
     * The overridden head() method that throws an exception when called
     * when the target is a Sentinel.
     */
    @Override
    public Object head() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    /**
     * The overriden tail() method that throws an exception when called
     * when the target is a Sentinel.
     */
    @Override
    public InfiniteList<Object> tail() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    /**
     * The overridden map method that returns a Sentinel.
     *
     * @param mapper A Transformer that is not used.
     * @return A Sentinel.
     */
    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return sentinel();
    }

    /**
     * The overridden filter method that returns a Sentinel.
     *
     * @param predicate A BooleanCondition that is not used.
     * @return A Sentinel.
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return sentinel();
    }

    /**
     * The overridden limit method that returns a Sentinel.
     *
     * @param n A long that is not used.
     * @return A Sentinel.
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return sentinel();
    }

    /**
     * The overridden takeWhile method that returns a Sentinel.
     *
     * @param predicate A BooleanCondition that is not used.
     * @return A Sentinel.
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return sentinel();
    }
  }

  /**
   * Returns a sentinel.
   *
   * @param <T> The type of Sentinel returned.
   * @return A Sentinel.
   */
  public static <T> InfiniteList<T> sentinel() {
    //We can suppress this as we know that Sentinel is of type InfiniteList.
    @SuppressWarnings("unchecked")
    InfiniteList<T> finite = (InfiniteList<T>) Sentinel.SENTINEL;
    return finite;
  }

  /**
   * Returns the InfiniteList with equal to or less than n elements.
   *
   * @param n The limit imposed on the InfiniteList.
   * @return The InfiniteList with equal to or less than n elements.
   */
  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return sentinel();
    }
    return new InfiniteList<T>(this.head, 
        Lazy.of(() -> this.tail.get().limit(head.get().map(x -> n - 1).orElse(n))));
  }
 
  /**
   * Returns the InfiniteList up to the first element that does not satisfy the given
   * BooleanCondition.
   *
   * @param predicate The BooleanCondition to evaluate the elements of the InfiniteList.
   * @return The InfiniteList with elements up to the first element that does not satisfy
   the given BooleanCondition predicate.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> newHead = Lazy.of(() -> this.head.get().filter(predicate));
    return new InfiniteList<T>(newHead, Lazy.of(() -> this.head.get().map(x -> newHead.get()
            .map(y -> this.tail.get().takeWhile(predicate))
            .orElse(sentinel())).orElseGet(() -> this.tail.get().takeWhile(predicate))));
  }

  /**
   * Checks if target is a Sentinel.
   *
   * @return The boolean to show if the target is a Sentinel.
   */
  public boolean isSentinel() {
    return this instanceof Sentinel;
  }

  /**
   * Combines all the elements of the target InfiniteList together.
   *
   * @param <U> The type of value returned.
   * @param identity The base value to be added on to.
   * @param accumulator The Combiner that combines the target's elements.
   * @return The resultant value.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    List<T> reduced = this.toList();
    int index = this.toList().toArray().length - 1; 
    while (!reduced.isEmpty()) {
      identity = accumulator.combine(identity, reduced.remove(index));
      index--;
    }
    return identity;
  }

  /**
   * Counts the number of elements in the target InfiniteList.
   *
   * @return The number of elements in the target InfiniteList.
   */
  public long count() {
    return (long) this.toList().toArray().length;
  }

  /**
   * Converts the InfiniteList to a List type.
   *
   * @return The list converted from the InfiniteList.
   */
  public List<T> toList() {
    List<T> list = new ArrayList<>();
    InfiniteList<T> infinity = this;
    while (!infinity.isSentinel()) {
      Consumer<T> temp = t -> {
        list.add(t);
      };
      
      infinity.head.get().consumeWith(temp);
      infinity = infinity.tail.get();
    }
    return list;
  }

  /**
   * Return the String representation of the InfiniteList.
   *
   * @return The String representation of the InfiniteList.
   */
  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
