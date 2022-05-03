/**
 * @author Nicholas Arlin Halim (Group B08K)
 */ 
class ShopDeparture extends Event {

  private Customer customer;
  private Shop shop;

  public ShopDeparture(Customer customer, Shop shop, double endTime) {
    super(endTime);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": " + this.customer + " departed");
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
