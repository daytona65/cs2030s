/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class Shop {
  private Array<Counter> counterArray;
  private Queue<Customer> q;
  private int counterqueueSize;
  private int numOfCounters;

  public Shop(int numOfCounters, int queueSize, int counterqueueSize) {
    this.counterArray = new Array<Counter>(numOfCounters);
    this.q = new Queue<Customer>(queueSize);
    this.counterqueueSize = counterqueueSize;
    this.numOfCounters = numOfCounters;
    for (int i = 0; i < numOfCounters; i++) {
      this.counterArray.set(i, new Counter(true, i, counterqueueSize));
    }
  }

  public Counter getfreeCounter() {
    for (int i = 0; i < this.numOfCounters; i++) {
      if (this.counter(i).getstate()) {
        return this.counter(i);
      }
    }
    return null;
  }

  public Counter counter(int index) {
    return this.counterArray.get(index);
  }

  public Counter minqueue() {
    return this.counterArray.min();
  }

  public boolean hasfreeCounters() {
    for (int i = 0; i < this.numOfCounters; i++) {
      if (this.counter(i).getstate()) {
        return true;
      }
    }
    return false;
  }

  public boolean hasfreeCounterQueues() {
    for (int i = 0; i < this.numOfCounters; i++) {
      if (!this.counterArray.get(i).qisFull()) {
        return true;
      }
    }
    return false;
  }

  public void enq(Customer customer) {
    q.enq(customer);
  } 
  
  public Customer deq() {
    return (Customer) q.deq();
  }
  
  public String qtoString() {
    return q.toString();
  }
  
  public boolean qisFull() {
    return q.isFull();
  }
  
  public boolean qisEmpty() {
    return q.isEmpty();
  }
  
  public int qlength() {
    return q.length();
  }
}

