package tankbattle.core.shape;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.HashSet;
import java.util.Set;

import tankbattle.core.position.Point;

/**
 * 表示一个圆形，具有半径<br>
 * 
 * @author Gogo
 *
 */
public class Circle extends Shape {

	private double radius;

	public Circle(double radius) {
		this.radius = Math.abs(radius);
	}

	@Override
	public Set<Point> points() {
		Set<Point> set = new HashSet<Point>();
		for (double y = -radius; y <= radius + accuracy / 2; y += accuracy) {
			double w = sqrt(pow(radius, 2) - pow(y, 2));
			for (double x = -w; x <= w; x += accuracy) {
				set.add(new Point(x, y));
			}
		}
		return set;
	}

	@Override
	public boolean contains(Point p) {
		return p.toVector().length() <= radius + accuracy / 2;
	}

	public double getRadius() {
		return radius;
	}

	public Circle setRadius(double radius) {
		this.radius = Math.abs(radius);
		return this;
	}

	@Override
	public String toString() {
		return "Circle {radius:" + radius + "}";
	}

	@Override
	public Rect boundingRect() {
		return new Rect(radius * 2, radius * 2);
	}

}
