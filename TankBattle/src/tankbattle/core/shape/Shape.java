package tankbattle.core.shape;

import java.util.HashSet;
import java.util.Set;

import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;

/**
 * 描述一个形状<br>
 * 
 * @author Gogo
 *
 */
public abstract class Shape {

	protected double accuracy = 1;

	public abstract Set<Point> points();

	public abstract Rect boundingRect();

	public abstract boolean contains(Point p);

	public Set<Point> rpoints() {
		Set<Point> ps = new HashSet<>();
		points().forEach(p -> {
			ps.add(p.rval());
		});
		return ps;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VectorShape toVectorShape(Vector v) {
		return new VectorShape(this, v);
	}

	public double boundingRectWidth() {
		return boundingRect().getWidth();
	}

	public double boundingRectHeight() {
		return boundingRect().getHeight();
	}

	public double accuracy() {
		return accuracy;
	}

	public Shape setAccuracy(double accuracy) {
		this.accuracy = accuracy;
		return this;
	}

}
