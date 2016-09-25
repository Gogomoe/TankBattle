package tankbattle.core.shape;

import java.util.HashSet;
import java.util.Set;

import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;

/**
 * 具有位置的图形<br>
 * 
 * @author Gogo
 *
 */
public class VectorShape<S extends Shape> {

	protected S shape;
	protected Vector vector;

	public VectorShape(S shape, Vector vector) {
		this.shape = shape;
		this.vector = vector;
	}

	public boolean contains(Point p) {
		return shape.contains(p.subtract(vector));
	}

	@SuppressWarnings("unchecked")
	public <U extends Shape> boolean contains(VectorShape<U> s) {
		if (s == null) {
			throw new NullPointerException("contains对象为空");
		}

		ShapeComparator<S, U> comp = (ShapeComparator<S, U>) ComparatorFactory.factory.get(this.getShape().getClass(),
				s.getShape().getClass());
		if (comp != null) {
			return comp.contains(this, s);
		}
		Set<Point> ps = points();

		for (Point p : s.points()) {
			if (!ps.contains(p)) {
				return false;
			}
		}

		return true;
	}

	public <U extends Shape> boolean contacts(VectorShape<U> s) {
		if (s == null) {
			throw new NullPointerException("contacts对象为空");
		}
		@SuppressWarnings("unchecked")
		ShapeComparator<S, U> comp = (ShapeComparator<S, U>) ComparatorFactory.factory.get(this.getShape().getClass(),
				s.getShape().getClass());
		if (comp != null) {
			return comp.contacts(this, s);
		}
		Set<Point> ps = points();

		for (Point p : s.points()) {
			if (ps.contains(p)) {
				return true;
			}
		}

		return false;
	}

	public Set<Point> points() {
		Set<Point> ps = new HashSet<>();
		shape.points().forEach(p -> {
			ps.add(p.add(vector));
		});
		return ps;
	}

	public Set<Point> rpoints() {
		Set<Point> ps = new HashSet<>();
		points().forEach(p -> {
			ps.add(p.rval());
		});
		return ps;
	}

	public VectorShape<Rect> boundingRect() {
		return new VectorShape<Rect>(shape.boundingRect(), vector);
	}

	public double boundingRectWidth() {
		return shape.boundingRect().getWidth();
	}

	public double boundingRectHeight() {
		return shape.boundingRect().getHeight();
	}

	public S getShape() {
		return shape;
	}

	public Vector getVector() {
		return vector;
	}

	public VectorShape<S> toVectorShape(Vector v) {
		return new VectorShape<S>(this.shape, v);
	}

	@Override
	public String toString() {
		return "VectorShape {shape=" + shape + ", vector=" + vector + "}";
	}

}
