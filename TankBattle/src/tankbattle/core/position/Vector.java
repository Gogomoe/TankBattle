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

	public Vector set(Vector v) {
		this.x = v.x;
		this.y = v.y;
		this.accuracy = v.accuracy;
		return this;
	}

	public double getX() {
		return x;
	}

	public Vector setX(int x) {
		this.x = x;
		return this;
	}

	public double getY() {
		return y;
	}

	public Vector setY(int y) {
		this.y = y;
		return this;
	}

	@Override
	public String toString() {
		return " ->(" + x + ", " + y + ')';
	}

	@Override
	public boolean equals(Object obj) {
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

	public Vector setAccuracy(double accuracy) {
		this.accuracy = accuracy;
		return this;
	}
}
