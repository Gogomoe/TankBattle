package tankbattle.core.shape;

import java.util.HashSet;
import java.util.Set;

import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;

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
		for (double y = -height / 2; y <= height / 2 + accuracy / 2; y += accuracy) {
			for (double x = -width / 2; x <= width / 2 + accuracy / 2; x += accuracy) {
				set.add(new Point(x, y));
			}
		}
		return set;
	}

	@Override
	public VRect toVectorShape(Vector v) {
		return new VRect(this, v);
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

}
