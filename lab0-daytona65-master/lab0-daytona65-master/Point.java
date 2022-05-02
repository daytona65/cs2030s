/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2021/22
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author XXX
 */
class Point {
  // TODO
  private double x;
  private double y;

	public Point(double a, double b) {
		this.x = a;
		this.y = b;
	}
	
	@Override
	public String toString() {
		String a = "(" + this.x + ", " + this.y + ")";
	       	return a;
	}

	public double[] getXY() {
		double[] array = new double[2];
		array[0] = this.x;
		array[1] = this.y;
		return array;
	}	
}
