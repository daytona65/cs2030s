/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class Customer {
  private int customerID = 0;
  private double arrivalTime = 0;
  private double serviceTime = 0;

  public int getID() {
    return this.customerID;
  }

  public Customer(int id, double arrivalTime, double serviceTime) {
    this.customerID = id;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }

  public double getarrivalTime() {
    return this.arrivalTime;
  }

  public double getserviceTime() {
    return this.serviceTime;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format("C%d", this.customerID);
    return str;
  }
}
