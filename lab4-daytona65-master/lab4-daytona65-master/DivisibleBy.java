/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Nicholas Arlin Halim (B08K)
 */

class DivisibleBy implements BooleanCondition<Integer> {
  private Integer a;

  public DivisibleBy(int a) {
    this.a = (Integer) a;
  }
  
  public boolean test(Integer b) {
    Integer c = (Integer) b;
    return c % a == 0;
  }
}
