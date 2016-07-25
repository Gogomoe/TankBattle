package tankbattle.core.shape;

import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;
import tankbattle.core.shape.ShapeComparator.RectComparator;

public class VRect extends VectorShape {

	static {
		ComparatorFactory.factory.add(VRect.class, VRect.class, new RectComparator());
	}

	private Rect rect;

	public VRect(Rect rect, Vector vector) {
		super(rect, vector);
		this.rect = rect;
	}

	@Override
	public boolean contains(Point p) {
		double x = vector.getX(), y = vector.getY(), vx = p.getX(), vy = p.getY(), ha = accuracy / 2,
				hw = rect.getWidth() / 2, hh = rect.getHeight() / 2;
		return (vx <= x + hw + ha && vx >= x - hw - ha && vy <= y + hh + ha && vy >= y - hh - ha);
	}

	@Override
	public VRect toVectorShape(Vector v) {
		return new VRect(rect, v);
	}

	public Rect getRect() {
		return rect;
	}

	public VRect setRect(Rect rect) {
		this.rect = rect;
		return this;
	}

}
