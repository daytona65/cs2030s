import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Wei Tsang
 * @version CS2030S AY21/22 Semester 2
 */ 
class ShopSimulation extends Simulation {
  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *ewfewf         sequence of (arrival time, service time) pair, each
   *             pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {
    Array.CreateinitEvents(sc.nextInt()); 
    int numOfCounters = sc.nextInt();

   
    Array.Createavailable(numOfCounters);
    for (int i = 0; i<numOfCounters; i++) {
	    Array.Setavailable(i, true);
    }

    int id = 0; 
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble(); 
      double serviceTime = sc.nextDouble();
      Event Arrival = new ShopArrival(arrivalTime, id, serviceTime);
      Array.SetinitEvents(id, Arrival);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return Array.GetinitEvents(); //Returns list of events to Simulator
  }
}
