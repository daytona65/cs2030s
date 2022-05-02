import java.util.Random;

// TODO
class RandomPoint extends Point {
	private static Random rng = new Random(1);

	public RandomPoint(double minX, double maxX, double minY, double maxY) {
		super(minX + (maxX - minX) * RandomPoint.rng.nextDouble(), minY + (maxY - minY) * RandomPoint.rng.nextDouble());
	}


	public static void setSeed(int s) {
		RandomPoint.rng = new Random(s);
	}
}
