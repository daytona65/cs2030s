class ShopDeparture extends Event {

	private int customerId;

	public ShopDeparture(int customerId, double endTime) {
		super(endTime);
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		String str = "";
		str = String.format(": Customer %d departed", this.customerId);
		return super.toString() + str;
	}

	@Override
	public Event[] simulate() {
		return new Event[] {};
	}
}	
