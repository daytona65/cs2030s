/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class ShopArrival extends Event {

  private Customer customer;
  private Shop shop;


  public ShopArrival(Customer customer, Shop shop, double arrivalTime) {
    super(arrivalTime);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": " + this.customer + " arrived " + this.shop.qtoString());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {     
    if (this.shop.hasfreeCounters()) {
      return new Event[] {
        new ShopServiceBegin(this.customer, this.shop.getfreeCounter(), this.shop, super.getTime())
      };
    } else if (this.shop.hasfreeCounterQueues()) {
      Counter c = this.shop.minqueue();
      return new Event[] {
        new CounterQueue(this.customer, c, super.getTime())
      };
    } else if (!this.shop.qisFull()) {
      return new Event[] {
        new ShopQueue(this.customer, this.shop)
      };
    }
    return new Event [] {
      new ShopDeparture(this.customer, this.shop, this.customer.getarrivalTime())
    };
  }
}


