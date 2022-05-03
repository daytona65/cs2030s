/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class Counter implements Comparable<Counter> {
  private boolean state;
  private int id;
  private Queue<Customer> q;

  public Counter(boolean value, int id, int queueSize) {
    this.state = value;
    this.id = id;
    this.q = new Queue<Customer>(queueSize);
  }

  public boolean getstate() {
    return this.state;
  }

  public void state(boolean setvalue) {
    this.state = setvalue;
  }

  public void enq(Customer customer) {
    q.enq(customer);
  }

  public Customer deq() {
    return q.deq();
  }

  public String qtoString() {
    return q.toString();
  }

  public boolean qisFull() {
    return q.isFull();
  }

  public boolean qisEmpty() {
    return q.isEmpty();
  }

  public int qlength() {
    return q.length();
  }

  @Override
  public int compareTo(Counter c) {
    if (this.qlength() == c.qlength()) {
      return 0;
    } else if (this.qlength() < c.qlength()) {
      return -1;
    } else {
      return 1;
    }
  }


  @Override
  public String toString() {
    String str = "";
    str = String.format("S%d " + this.q, this.id);
    return str;
  }
}

