class ShopServiceEnd extends Event {

	private double endTime;
	private int customerId;
	private int counterId;

	public ShopServiceEnd(double endTime, int customerId, int counterId) {
		super(endTime);
		this.endTime = endTime;
		this.customerId = customerId;
		this.counterId = counterId;
	}

	@Override
	public String toString() {
		String str = "";
		str = String.format(": Customer %d service done (by Counter %d)", this.customerId, this.counterId);
		return super.toString() + str;
	}

	@Override
	public Event[] simulate() {
		Array.Setavailable(this.counterId, true);
		return new Event[] {
			new ShopDeparture(this.customerId, this.endTime)
		};
	}
}
		
