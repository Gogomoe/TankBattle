package tankbattle.core.shape;

import tankbattle.core.shape.ShapeComparator.RectCircleComparator;
import tankbattle.core.shape.ShapeComparator.CircleComparator;
import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;
import tankbattle.core.shape.ShapeComparator.CircleRectComparator;

public class VCircle extends VectorShape {

	static {
		ComparatorFactory.factory.add(VCircle.class, VCircle.class, new CircleComparator());
		ComparatorFactory.factory.add(VCircle.class, VRect.class, new CircleRectComparator());
		ComparatorFactory.factory.add(VRect.class, VCircle.class, new RectCircleComparator());
	}

	private Circle circle;

	public VCircle(Circle circle, Vector vector) {
		super(circle, vector);
		this.circle = circle;
	}

	@Override
	public boolean contains(Point p) {
		return p.subtract(vector).toVector().rval().length() <= circle.getRadius();
	}

	@Override
	public VCircle toVectorShape(Vector v) {
		return new VCircle(circle, v);
	}

	public Circle getCircle() {
		return circle;
	}

	public VCircle setCircle(Circle circle) {
		this.circle = circle;
		return this;
	}
}
