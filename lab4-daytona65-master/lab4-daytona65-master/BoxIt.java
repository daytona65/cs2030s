/**
 * Takes an item and return the item in a box.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Nicholas Arlin Halim (B08K)
 */

class BoxIt<T> implements Transformer<T, Box<T>> {

  public BoxIt() {
  }

  public Box<T> transform(T a) {
    return Box.of(a);
  }
}


