/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Lab 5
 * AY21/22 Semester 2
 *
 * @author Nicholas Arlin Halim (B08K)
 */

package cs2030s.fp;

@FunctionalInterface
public interface Transformer<T, U> {

  public U transform(T a);


}



