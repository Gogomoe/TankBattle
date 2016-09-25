package tankbattle.core.shape;

import static java.lang.Math.abs;
import java.util.HashSet;
import java.util.Set;

import tankbattle.core.position.Point;

/**
 * 表示一个矩形<br>
 * 具有宽、高属性<br>
 * 
 * @author Gogo
 *
 */
public class Rect extends Shape {

	private double width, height;

	public Rect(double width, double height) {
		this.width = Math.abs(width);
		this.height = Math.abs(height);
	}

	public Rect() {
	}

	@Override
	public Set<Point> points() {
		Set<Point> set = new HashSet<Point>();
		for (double y = -height / 2; y <= (height + accuracy) / 2 / 2; y += accuracy) {
			for (double x = -width / 2; x <= (width + accuracy) / 2 / 2; x += accuracy) {
				set.add(new Point(x, y));
			}
		}
		return set;
	}

	@Override
	public boolean contains(Point p) {
		return abs(p.getX()) <= (width + accuracy) / 2 && abs(p.getY()) <= (height + accuracy) / 2;
	}

	public double getWidth() {
		return width;
	}

	public Rect setWidth(double width) {
		this.width = Math.abs(width);
		return this;
	}

	public double getHeight() {
		return height;
	}

	public Rect setHeight(double height) {
		this.height = Math.abs(height);
		return this;
	}

	@Override
	public String toString() {
		return "Rect {width:" + width + ", height:" + height + "}";
	}

	@Override
	public Rect boundingRect() {
		return new Rect(width, height);
	}

}
