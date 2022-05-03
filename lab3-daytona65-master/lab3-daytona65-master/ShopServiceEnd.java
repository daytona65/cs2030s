/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class ShopServiceEnd extends Event {

  private Customer customer;
  private Counter counter;
  private Shop shop;

  public ShopServiceEnd(Customer customer, Counter counter, Shop shop, double endTime) {
    super(endTime);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": " + this.customer + " service done (by " + 
        this.counter + ")");
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter.state(true);
    if (!this.counter.qisEmpty()) {
      Customer c1 = this.counter.deq();
      if (this.shop.qisEmpty()) {
        return new Event[] {
          new ShopDeparture(this.customer, this.shop, super.getTime()),
          new ShopServiceBegin(c1, this.counter, this.shop, super.getTime()),
        };
      } else if (!this.shop.qisEmpty() && this.shop.hasfreeCounterQueues()) {
        Customer c2 = this.shop.deq();
        Counter counter1 = this.shop.minqueue();
        return new Event[] {
          new ShopDeparture(this.customer, this.shop, super.getTime()),
          new ShopServiceBegin(c1, this.counter, this.shop, super.getTime()),
          new CounterQueue(c2, counter1, super.getTime()),
        };
      } 
    } else if (this.counter.qisEmpty() && !this.shop.qisEmpty()) {
      Customer qcustomer = this.shop.deq();
      return new Event[] {
        new ShopDeparture(this.customer, this.shop, super.getTime()),
        new ShopServiceBegin(qcustomer, this.counter, this.shop, super.getTime())
      };
    }
    return new Event[] {
      new ShopDeparture(this.customer, this.shop, super.getTime())
    };
  }
}

