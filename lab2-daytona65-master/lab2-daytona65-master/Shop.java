/**
 * @author Nicholas Arlin Halim (Group B08K)
 */
class Shop {
  private static Counter[] available;
  private static Queue q;

  public static void createavailable(int size) {
    Shop.available = new Counter[size];
  }

  public static Counter[] getavailable() {
    return Shop.available;
  }

  public static void setavailable(int position, boolean value) {
    Shop.available[position] = new Counter(value);
  }

  public static void makeq(int size) {
    Shop.q = new Queue(size);
  }

  public static void enq(String str) {
    Shop.q.enq(str);
  } 
  
  public static String deq() {
    String s = (String) Shop.q.deq();
    return s;
  }
  
  public static String queuetostring() {
    String s = Shop.q.toString();
    return s;
  }
  
  public static boolean isFull() {
    return Shop.q.isFull();
  }
  
  public static boolean isEmpty() {
    return Shop.q.isEmpty();
  }
  
  public static int qlength() {
    return Shop.q.length();
  }
}

