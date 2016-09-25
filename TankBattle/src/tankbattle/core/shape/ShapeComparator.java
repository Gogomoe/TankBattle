package tankbattle.core.shape;

import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;

/**
 * 比较两个图形是否接触、包含的比较器
 * 
 * @author Gogo
 *
 */
public interface ShapeComparator<T extends Shape, S extends Shape> {

	public boolean contains(VectorShape<T> s1, VectorShape<S> s2);

	public boolean contacts(VectorShape<T> s1, VectorShape<S> s2);

	/**
	 * 矩形比较器
	 * 
	 * @author Gogo
	 *
	 */
	public static class RectComparator implements ShapeComparator<Rect, Rect> {

		@Override
		public boolean contains(VectorShape<Rect> s1, VectorShape<Rect> s2) {
			double hw = s2.getShape().getWidth() / 2, hh = s2.getShape().getHeight() / 2;
			double x = s2.vector.getX(), y = s2.getVector().getY();
			return s1.contains(new Point(x + hw, y + hh)) && s1.contains(new Point(x + hw, y - hh))
					&& s1.contains(new Point(x - hw, y + hh)) && s1.contains(new Point(x - hw, y - hh));
		}

		@Override
		public boolean contacts(VectorShape<Rect> s1, VectorShape<Rect> s2) {
			double hw = s2.getShape().getWidth() / 2, hh = s2.getShape().getHeight() / 2;
			double x = s2.vector.getX(), y = s2.getVector().getY();
			return s1.contains(new Point(x + hw, y + hh)) || s1.contains(new Point(x + hw, y - hh))
					|| s1.contains(new Point(x - hw, y + hh)) || s1.contains(new Point(x - hw, y - hh));
		}

	}

	/**
	 * 圆比较器
	 * 
	 * @author Gogo
	 *
	 */
	public static class CircleComparator implements ShapeComparator<Circle, Circle> {

		@Override
		public boolean contains(VectorShape<Circle> s1, VectorShape<Circle> s2) {
			Vector d = s2.getVector().subtract(s1.getVector());
			return d.length() + s2.getShape().getRadius() <= s1.getShape().getRadius();
		}

		@Override
		public boolean contacts(VectorShape<Circle> s1, VectorShape<Circle> s2) {
			Vector d = s2.getVector().subtract(s1.getVector());
			return d.length() <= s2.getShape().getRadius() + s1.getShape().getRadius();
		}

	}

	/**
	 * 圆和矩形比较器
	 * 
	 * @author Gogo
	 *
	 */
	public static class CircleRectComparator implements ShapeComparator<Circle, Rect> {

		@Override
		public boolean contains(VectorShape<Circle> c, VectorShape<Rect> r) {
			Vector cv = c.getVector();
			Vector rv = r.getVector();
			Vector d = rv.subtract(cv);

			if (d.length() > c.getShape().getRadius()) {
				return false;
			}
			double hw = r.getShape().getWidth() / 2, hh = r.getShape().getHeight() / 2;
			return (c.contains(rv.add(new Vector(hw, hh)).toPoint()))
					&& (c.contains(rv.add(new Vector(-hw, hh)).toPoint()))
					&& (c.contains(rv.add(new Vector(hw, -hh)).toPoint()))
					&& (c.contains(rv.add(new Vector(-hw, -hh)).toPoint()));
		}

		@Override
		public boolean contacts(VectorShape<Circle> c, VectorShape<Rect> r) {
			Vector cv = c.getVector();
			Vector rv = r.getVector();
			Vector d = rv.subtract(cv);
			if (d.length() > c.getShape().getRadius()
					+ sqrt(pow(r.getShape().getWidth() / 2, 2) + pow(r.getShape().getHeight() / 2, 2))) {
				return false;
			}
			if (d.length() < c.getShape().getRadius() + min(r.getShape().getWidth(), r.getShape().getHeight()) / 2) {
				return true;
			}
			return r.contains(cv.add(d.multiply(c.getShape().getRadius() / d.length())).toPoint());
		}
	}

	/**
	 * 矩形和圆比较器
	 * 
	 * @author Gogo
	 *
	 */
	public static class RectCircleComparator implements ShapeComparator<Rect, Circle> {

		@Override
		public boolean contains(VectorShape<Rect> r, VectorShape<Circle> c) {
			Vector cv = c.getVector();
			Vector rv = r.getVector();
			Vector d = cv.subtract(rv);

			double hw = r.getShape().getWidth() / 2, hh = r.getShape().getHeight() / 2;

			if (d.length() > sqrt(pow(hw, 2) + pow(hh, 2))) {
				return false;
			}
			if (d.length() <= 0.1) {
				return c.getShape().getRadius() <= min(hw, hh);
			}

			return r.contains(rv.add(d.multiply((d.length() + c.getShape().getRadius()) / d.length())).toPoint());

		}

		@Override
		public boolean contacts(VectorShape<Rect> r, VectorShape<Circle> c) {
			Vector cv = c.getVector();
			Vector rv = r.getVector();
			Vector d = rv.subtract(cv);
			if (d.length() > c.getShape().getRadius()
					+ sqrt(pow(r.getShape().getWidth() / 2, 2) + pow(r.getShape().getHeight() / 2, 2))) {
				return false;
			}
			if (d.length() < c.getShape().getRadius() + min(r.getShape().getWidth(), r.getShape().getHeight()) / 2) {
				return true;
			}
			return r.contains(cv.add(d.multiply(c.getShape().getRadius() / d.length())).toPoint());
		}

	}

}
