/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class ShopArrival extends Event {

  private double arrivalTime;
  private int customerId;
  private double serviceTime;


  public ShopArrival(double arrivalTime, int customerId, double serviceTime) {
    super(arrivalTime);
    this.arrivalTime = arrivalTime;
    this.customerId = customerId;
    this.serviceTime = serviceTime;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d arrived " + Shop.queuetostring(), this.customerId);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    //Check for counters
    for (int i = 0; i < Shop.getavailable().length; i++) {
      if (Shop.getavailable()[i].getstate()) {
        return new Event[] {
          new ShopServiceBegin(customerId, serviceTime, i, super.getTime())
        };
      }
    }
    //If no counters check the queue
    if (!Shop.isFull()) {
      return new Event[] {
        new ShopQueue(this.arrivalTime, this.customerId, this.serviceTime)
      };
    }
    return new Event [] {
      new ShopDeparture(this.customerId, -1, this.arrivalTime)
    };
  }
}


