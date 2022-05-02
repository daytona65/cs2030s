import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 2, 2021/22
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author XXX 
 */

class Lab0 {

  public static double estimatePi(int numOfPoints, int seed) {
  	RandomPoint.setSeed(seed);
	//pi = 4x n in circle/total points
	int counter = 0;
	for(int i = 0; i < numOfPoints; i++) {
		Point p = new RandomPoint(0, 1, 0, 1);
		Point centre = new Point(0.5,0.5);
		Circle lol = new Circle(centre, 0.5);

		if (lol.contains(p)) {
			counter++;
		} else {
			continue;
		}
	}
	double four = 4;
	return (four * counter)/numOfPoints;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
