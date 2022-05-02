/**
 * @author Nicholas Arlin Halim (Group B08K)
 */ 
class ShopDeparture extends Event {

  private int customerId;
  private int counterId;

  public ShopDeparture(int customerId, int counterId, double endTime) {
    super(endTime);
    this.customerId = customerId;
    this.counterId = counterId;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d departed", this.customerId);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    if (this.counterId >= 0 && !Shop.isEmpty()) {
      int id = Integer.parseInt(Character.toString(Shop.deq().charAt(1)));
      return new Event[] {
        new ShopServiceBegin(id, Customer.service(id), this.counterId, super.getTime())
      };
    }
    return new Event[] {};
  }
}
