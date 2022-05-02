/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class Customer {
  private static int customerID = 0;
  private static double[] arrivalTime;
  private static double[] serviceTime;

  public static int getID() {
    return Customer.customerID;
  }

  public static void addID(int i) {
    Customer.customerID += i;
  }

  public static void createTimeArrays(int size) {
    Customer.arrivalTime = new double[size];
    Customer.serviceTime = new double[size];
  }

  public static void saveID(double arrivalTime, double serviceTime) {
    Customer.arrivalTime[Customer.customerID] = arrivalTime;
    Customer.serviceTime[Customer.customerID] = serviceTime;
  }

  public static double arrival(int id) {
    return Customer.arrivalTime[id];
  }

  public static double service(int id) {
    return Customer.serviceTime[id];
  }
}
