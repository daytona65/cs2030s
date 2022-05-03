/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class ShopQueue extends Event {

  private Customer customer;
  private Shop shop;

  public ShopQueue(Customer customer, Shop shop) {
    super(customer.getarrivalTime());
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": " + this.customer + " joined shop queue " + shop.qtoString());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.shop.enq(this.customer);
    return new Event[] {};
  }
}
