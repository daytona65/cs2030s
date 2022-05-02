class Array {
	private static boolean[] available; //Counters

	private static Event[] initEvents; //Event array

	public static void Createavailable (int i) {
		Array.available = new boolean[i];
	}

	public static void CreateinitEvents (int i) {
		Array.initEvents = new Event[i];
	}

	public static boolean[] Getavailable() {
		return Array.available;
	}
	
	public static void Setavailable(int position, boolean value) {
		Array.available[position] = value;
	}

	public static Event[] GetinitEvents() {
		return Array.initEvents;
	}

	public static void SetinitEvents (int position, Event value) {
		Array.initEvents[position] = value;
	}
}
			
