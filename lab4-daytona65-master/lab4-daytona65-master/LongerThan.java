/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Nicholas Arlin Halim (B08K)
 */

class LongerThan implements BooleanCondition<String> {
  private int limit;

  public LongerThan(int limit) {
    this.limit = limit;
  }

  public boolean test(String s) {
    String a = (String) s;
    return a.length() > limit;
  }
}



