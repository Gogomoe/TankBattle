package tankbattle.core.position;

import static java.lang.Math.rint;

/**
 * 用于表示平面上位置的一个点<br/>
 * 具有X和Y两个坐标<br/>
 *
 * @author Gogo
 */
public class Point {

	protected double accuracy = 1;

	private double x, y;

	public Point() {

	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(double x, double y, double accuracy) {
		this.x = x;
		this.y = y;
		this.accuracy = accuracy;
	}

	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
		this.accuracy = p.accuracy;
	}

	public Point add(Vector v) {
		return new Point(x + v.getX(), y + v.getY(), accuracy);
	}

	public Point subtract(Vector v) {
		return new Point(x - v.getX(), y - v.getY(), accuracy);
	}

	public Vector distance(Point p) {
		return p.toVector().subtract(this.toVector());
	}

	public Vector toVector() {
		return new Vector(x, y, accuracy);
	}

	public Point copy() {
		return new Point(this);
	}

	public Point rval() {
		return new Point(accuracy * rint(x / accuracy), accuracy * rint(y / accuracy), accuracy);
	}

	public Point set(Point p) {
		this.x = p.x;
		this.y = p.y;
		this.accuracy = p.accuracy;
		return this;
	}

	public double getX() {
		return x;
	}

	public Point setX(double x) {
		this.x = x;
		return this;
	}

	public double getY() {
		return y;
	}

	public Point setY(double y) {
		this.y = y;
		return this;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point v = (Point) obj;
			double vx = v.getX(), vy = v.getY(), ha = accuracy / 2;
			return (vx <= x + ha && vx >= x - ha && vy <= y + ha && vy >= y - ha);
		}
		return false;
	}

	public double accuracy() {
		return accuracy;
	}

	public Point setAccuracy(double accuracy) {
		this.accuracy = accuracy;
		return this;
	}

}
