/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class ShopServiceBegin extends Event {

  private Customer customer;
  private Counter counter;
  private Shop shop;

  public ShopServiceBegin(Customer customer, Counter counter, Shop shop, double getTime) {
    super(getTime);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": " + this.customer + " service begin (by " + 
        this.counter + ")");
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter.state(false);
    double endTime = super.getTime() + this.customer.getserviceTime();
    return new Event[] {
      new ShopServiceEnd(this.customer, this.counter, this.shop, endTime)
    };
  }
}

