/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class CounterQueue extends Event {
  
  private Customer customer;
  private Counter counter;

  public CounterQueue(Customer customer, Counter counter, double departshopqueueTime) {
    super(departshopqueueTime);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": " + this.customer + " joined counter queue " + 
        "(at " + this.counter + ")");
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter.enq(this.customer);
    return new Event[] {};
  }
}
