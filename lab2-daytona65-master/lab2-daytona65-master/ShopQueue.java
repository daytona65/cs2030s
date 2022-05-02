/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class ShopQueue extends Event {

  private double arrivalTime;
  private int customerId;
  private double serviceTime;

  public ShopQueue(double arrivalTime, int customerId, double serviceTime) {
    super(arrivalTime);
    this.arrivalTime = arrivalTime;
    this.customerId = customerId;
    this.serviceTime = serviceTime;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d joined queue " + Shop.queuetostring(), this.customerId);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    Shop.enq("C" + String.valueOf(this.customerId));
    return new Event[] {};
  }
}
