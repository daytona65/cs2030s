class ShopServiceBegin extends Event {

	private int customerId;
	private double serviceTime;
	private int counterId;
	private double getTime;
	
	public ShopServiceBegin(int customerId, double serviceTime, int counterId, double getTime) {
		super(getTime);
		this.customerId = customerId;
		this.serviceTime = serviceTime;
		this.counterId = counterId;
		this.getTime = getTime;
	}

	@Override
	public String toString() {
		String str = "";
		str = String.format(": Customer %d service begin (by Counter %d)", this.customerId, this.counterId);
	return super.toString() + str;
	}

	@Override
	public Event[] simulate() {
		Array.Setavailable(counterId, false);
		double endTime = super.getTime() + this.serviceTime;
		return new Event[] {
			new ShopServiceEnd(endTime, this.customerId, this.counterId)
		};
	}
}

