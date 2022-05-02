import java.util.Scanner;

/**
 * @author Nicholas Arlin Halim (Group B08K) 
 */

class ShopSimulation extends Simulation {
  private static Event[] initEvents;

  public ShopSimulation(Scanner sc) {
    int n = sc.nextInt();
    ShopSimulation.initEvents = new Event[n];
    Customer.createTimeArrays(n); 
    int numOfCounters = sc.nextInt();
    int m = sc.nextInt();

    Shop.createavailable(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      Shop.setavailable(i, true);
    }

    Shop.makeq(m);

    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble(); 
      double serviceTime = sc.nextDouble();
      int id = Customer.getID();
      Event arrival = new ShopArrival(arrivalTime, id, serviceTime);
      ShopSimulation.setinitEvents(id, arrival);
      Customer.saveID(arrivalTime, serviceTime);
      Customer.addID(1);
    }
  }

  public static Event[] getinitEvents() {
    return ShopSimulation.initEvents;
  }

  public static void setinitEvents(int position, Event value) {
    ShopSimulation.initEvents[position] = value;
  }

  @Override
  public Event[] getInitialEvents() {
    return ShopSimulation.getinitEvents();
  }
}
