package square_method;

public class Point {

	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// WYPISUJE DANY PUNKT
	@Override
	public String toString() {
		return "( " + this.x + " ; " + this.y + " )";
	}

	// POBIERA X
	public double getX() {
		return x;
	}

	// USTALA WARTOSC X
	public void setX(double x) {
		this.x = x;
	}

	// POBIERA Y
	public double getY() {
		return y;
	}

	// USTALA WARTOSC Y
	public void setY(double y) {
		this.y = y;
	}
}
