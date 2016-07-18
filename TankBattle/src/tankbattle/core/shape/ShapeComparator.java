package tankbattle.core.shape;

import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import tankbattle.core.position.Vector;

public interface ShapeComparator<T extends VectorShape, S extends VectorShape> {

	public boolean contains(T s1, S s2);

	public boolean contacts(T s1, S s2);

	public static class CircleRectComparator implements ShapeComparator<VCircle, VRect> {

		@Override
		public boolean contains(VCircle c, VRect r) {
			Vector cv = c.getVector();
			Vector rv = r.getVector();
			Vector d = rv.subtract(cv);

			if (d.length() > c.getCircle().getRadius()) {
				return false;
			}
			double hw = r.getRect().getWidth() / 2, hh = r.getRect().getHeight() / 2;
			return (c.contains(rv.add(new Vector(hw, hh)).toPoint()))
					&& (c.contains(rv.add(new Vector(-hw, hh)).toPoint()))
					&& (c.contains(rv.add(new Vector(hw, -hh)).toPoint()))
					&& (c.contains(rv.add(new Vector(-hw, -hh)).toPoint()));
		}

		@Override
		public boolean contacts(VCircle c, VRect r) {
			Vector cv = c.getVector();
			Vector rv = r.getVector();
			Vector d = rv.subtract(cv);
			if (d.length() > c.getCircle().getRadius()
					+ sqrt(pow(r.getRect().getWidth() / 2, 2) + pow(r.getRect().getHeight() / 2, 2))) {
				return false;
			}
			if (d.length() < c.getCircle().getRadius() + min(r.getRect().getWidth(), r.getRect().getHeight()) / 2) {
				return true;
			}
			return r.contains(cv.add(d.multiply(c.getCircle().getRadius() / d.length())).toPoint());
		}
	}

	public static class RectCircleComparator implements ShapeComparator<VRect, VCircle> {

		@Override
		public boolean contains(VRect r, VCircle c) {
			Vector cv = c.getVector();
			Vector rv = r.getVector();
			Vector d = cv.subtract(rv);

			double hw = r.getRect().getWidth() / 2, hh = r.getRect().getHeight() / 2;

			if (d.length() > sqrt(pow(hw, 2) + pow(hh, 2))) {
				return false;
			}
			if (d.length() <= 0.1) {
				return c.getCircle().getRadius() <= min(hw, hh);
			}

			return r.contains(rv.add(d.multiply((d.length() + c.getCircle().getRadius()) / d.length())).toPoint());

		}

		@Override
		public boolean contacts(VRect r, VCircle c) {
			Vector cv = c.getVector();
			Vector rv = r.getVector();
			Vector d = rv.subtract(cv);
			if (d.length() > c.getCircle().getRadius()
					+ sqrt(pow(r.getRect().getWidth() / 2, 2) + pow(r.getRect().getHeight() / 2, 2))) {
				return false;
			}
			if (d.length() < c.getCircle().getRadius() + min(r.getRect().getWidth(), r.getRect().getHeight()) / 2) {
				return true;
			}
			return r.contains(cv.add(d.multiply(c.getCircle().getRadius() / d.length())).toPoint());
		}

	}

}
