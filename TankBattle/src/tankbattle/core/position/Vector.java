package tankbattle.core.position;

import static java.lang.Math.pow;
import static java.lang.Math.rint;
import static java.lang.Math.sqrt;

/**
 * Created by Gogo .
 */
public class Vector {

	protected double accuracy = 1;

	private double x;
	private double y;

	public Vector() {
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector(Direction dir, double length) {
		this(dir.vector().multiply(length));
	}

	public Vector(double x, double y, double accuracy) {
		this.x = x;
		this.y = y;
		this.accuracy = accuracy;
	}

	public Vector(Vector v) {
		this.x = v.x;
		this.y = v.y;
		this.accuracy = v.accuracy;
	}

	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y, accuracy);
	}

	public Vector subtract(Vector v) {
		return new Vector(x - v.x, y - v.y, accuracy);
	}

	public Vector multiply(double num) {
		return new Vector(x * num, y * num, accuracy);
	}

	public double length() {
		return sqrt(pow(x, 2) + pow(y, 2));
	}

	public Vector copy() {
		return new Vector(this);
	}

	public Point toPoint() {
		return new Point(x, y, accuracy);
	}

	public Vector rval() {
		return new Vector(accuracy * rint(x / accuracy), accuracy * rint(y / accuracy), accuracy);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return " ->(" + x + ", " + y + ')';
	}

	@Override
	public int hashCode() {
		return (int) (31 * Math.round(x) + 31 * Math.round(y));
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (obj instanceof Vector) {
			Vector v = (Vector) obj;
			double vx = v.getX(), vy = v.getY(), ha = accuracy / 2;
			return (vx <= x + ha && vx >= x - ha && vy <= y + ha && vy >= y - ha);
		}
		return super.equals(obj);
	}

	public double accuracy() {
		return accuracy;
	}

}
