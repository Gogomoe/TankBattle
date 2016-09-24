package tankbattle.core.position;

import tankbattle.core.others.Extrable;

/**
 * 实现本接口表示该对象有位置<br>
 * 对象应该具有X轴位置、Y轴位置、高度等属性<br>
 * <br>
 * 本类具有默认实现方式{@link Positioner}，实现本类可以参见{@link Extrable}的实现方式<br>
 * 
 * @see Positioner
 *
 * @author Gogo
 */
public interface Positionable {

	final public static int HIGHEST = 1000;
	final public static int HIGHER = 500;
	final public static int PLANE = 0;
	final public static int LOWER = -500;
	final public static int LOWSET = -1000;

	public Positionable positioner();

	default public Point position() {
		return positioner().position();
	}

	default public Positionable setPosition(Point p) {
		return positioner().setPosition(p);
	}

	default public Direction towards() {
		return positioner().towards();
	}

	default public Positionable setTowards(Direction d) {
		return positioner().setTowards(d);
	}

	default public int layer() {
		return positioner().layer();
	}

	default public Positionable setLayer(int layer) {
		return positioner().setLayer(layer);
	}

	default public double getX() {
		return positioner().getX();
	}

	default public double getY() {
		return positioner().getY();
	}

}
