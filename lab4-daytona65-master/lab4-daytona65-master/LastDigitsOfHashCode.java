/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Nicholas Arlin Halim (B08K)
 */

class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private Integer k;

  public LastDigitsOfHashCode(Integer k) {
    this.k = k;
  }

  public Integer transform(Object a) {
    int hashvalue = a.hashCode();
    hashvalue = Math.abs(hashvalue % ((int) Math.pow(10, k)));
    return hashvalue;
  }
}


