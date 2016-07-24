package tankbattle.core.shape;

import java.util.HashSet;
import java.util.Set;

import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;

public class VectorShape extends Shape {

	protected Shape shape;
	protected Vector vector;

	public VectorShape(Shape shape, Vector vector) {
		this.shape = shape;
		this.vector = vector;
	}

	public boolean contains(Point p) {
		return shape.points().contains(p.subtract(vector));
	}

	@SuppressWarnings("unchecked")
	public <T extends VectorShape, S extends VectorShape> boolean contains(S s) {
		if (s == null) {
			throw new NullPointerException("contains对象为空");
		}

		ShapeComparator<T, S> comp = ComparatorFactory.factory.get((Class<T>) this.getClass(), (Class<S>) s.getClass());
		if (comp != null) {
			return comp.contains((T) this, (S) s);
		}
		Set<Point> ps = points();

		for (Point p : s.points()) {
			if (!ps.contains(p)) {
				return false;
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public <T extends VectorShape, S extends VectorShape> boolean contacts(S s) {
		if (s == null) {
			throw new NullPointerException("contacts对象为空");
		}
		ShapeComparator<T, S> comp = ComparatorFactory.factory.get((Class<T>) this.getClass(), (Class<S>) s.getClass());
		if (comp != null) {
			return comp.contacts((T) this, (S) s);
		}
		Set<Point> ps = points();

		for (Point p : s.points()) {
			if (ps.contains(p)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Set<Point> points() {
		Set<Point> ps = new HashSet<>();
		shape.points().forEach(p -> {
			ps.add(p.add(vector));
		});
		return ps;
	}

	public Shape getShape() {
		return shape;
	}

	public Vector getVector() {
		return vector;
	}

}
