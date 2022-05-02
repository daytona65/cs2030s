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
		str = String.format(": Customer %d arrives", this.customerId);
		return super.toString() + str;
	}

	@Override
	public Event[] simulate() {
		//Check for counters
		for (int i = 0; i<Array.Getavailable().length; i++) {
			if (Array.Getavailable()[i]) {
				return new Event[] {
					new ShopServiceBegin(customerId, serviceTime, i, super.getTime())
				};
			}
		}
		//If no counters depart
		return new Event [] {
			new ShopDeparture(this.customerId, this.arrivalTime)
		};
	}
}


