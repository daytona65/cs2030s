import java.util.Scanner;

/**
 * @author Nicholas Arlin Halim (Group B08K) 
 */

class ShopSimulation extends Simulation {
  private Event[] initEvents;

  public ShopSimulation(Scanner sc) {
    this.initEvents = new Event[sc.nextInt()]; 
    int numOfCounters = sc.nextInt();
    int counterqueueSize = sc.nextInt();
    int queueSize = sc.nextInt();

    Shop shop = new Shop(numOfCounters, queueSize, counterqueueSize);
    
    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble(); 
      double serviceTime = sc.nextDouble();
      Event arrival = new ShopArrival(new Customer(id, arrivalTime, serviceTime), 
          shop, arrivalTime);
      this.setinitEvents(id, arrival);
      id++;
    }
  }

  public Event[] getinitEvents() {
    return this.initEvents;
  }

  public void setinitEvents(int position, Event value) {
    this.initEvents[position] = value;
  }

  @Override
  public Event[] getInitialEvents() {
    return this.getinitEvents();
  }
}
