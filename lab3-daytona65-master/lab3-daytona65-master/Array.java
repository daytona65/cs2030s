/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class Array<T extends Comparable<T>> {
  private T[] array;

  //The only way we can put a Counter into this array is through
  //the method set() and we only put object of type Counter inside.
  //So it is safe to cast Object[] to T[], and safe to use
  //compareTo() in the Counter as we only put an object of
  //type Counter inside.
  @SuppressWarnings({"unchecked", "rawtypes"})
  Array(int size) {
    this.array = (T[]) new Comparable[size];
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {
    T small = this.array[0];
    for (int i = 0; i < this.array.length; i++) {
      if (this.array[i].compareTo(small) < 0) {
        small = this.array[i];
      } else {
        continue;
      }
    }
    return small;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
