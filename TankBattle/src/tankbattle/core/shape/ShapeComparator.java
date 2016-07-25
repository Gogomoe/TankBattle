package tankbattle.core.shape;

import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;

public interface ShapeComparator<T extends VectorShape, S extends VectorShape> {

	public boolean contains(T s1, S s2);

	public boolean contacts(T s1, S s2);

	public static class RectComparator implements ShapeComparator<VRect, VRect> {

		@Override
		public boolean contains(VRect s1, VRect s2) {
			double hw = s2.getRect().getWidth() / 2, hh = s2.getRect().getHeight() / 2;
			double x = s2.vector.getX(), y = s2.getVector().getY();
			return s1.contains(new Point(x + hw, y + hh)) && s1.contains(new Point(x + hw, y - hh))
					&& s1.contains(new Point(x - hw, y + hh)) && s1.contains(new Point(x - hw, y - hh));
		}

		@Override
		public boolean contacts(VRect s1, VRect s2) {
			double hw = s2.getRect().getWidth() / 2, hh = s2.getRect().getHeight() / 2;
			double x = s2.vector.getX(), y = s2.getVector().getY();
			return s1.contains(new Point(x + hw, y + hh)) || s1.contains(new Point(x + hw, y - hh))
					|| s1.contains(new Point(x - hw, y + hh)) || s1.contains(new Point(x - hw, y - hh));
		}

	}

	public static class CircleComparator implements ShapeComparator<VCircle, VCircle> {

		@Override
		public boolean contains(VCircle s1, VCircle s2) {
			Vector d = s2.getVector().subtract(s1.getVector());
			return d.length() + s2.getCircle().getRadius() <= s1.getCircle().getRadius();
		}

		@Override
		public boolean contacts(VCircle s1, VCircle s2) {
			Vector d = s2.getVector().subtract(s1.getVector());
			return d.length() <= s2.getCircle().getRadius() + s1.getCircle().getRadius();
		}

	}

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
