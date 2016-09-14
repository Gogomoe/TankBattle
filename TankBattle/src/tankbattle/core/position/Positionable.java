package tankbattle.core.position;

import tankbattle.core.TankBattle;
import tankbattle.core.others.Extrable;

/**
 * 实现本接口表示该对象有位置<br>
 * 对象应该具有X轴位置、Y轴位置、高度等属性<br>
 *
 * @author Gogo
 */
public interface Positionable extends Extrable {

	final public static int HIGHEST = 1000;
	final public static int HIGHER = 500;
	final public static int PLANE = 0;
	final public static int LOWER = -500;
	final public static int LOWSET = -1000;

	default public Point position() {
		return TankBattle.getGame().getProcess().send(new PositionPropertyEvent(this)).getPosition();
	}

	default public Positionable setPosition(Point p) {
		TankBattle.getGame().getProcess()
				.send(new PositionPropertyEvent(this, PositionPropertyEvent.SET_POSTION).setPosition(p));
		return this;
	}

	default public Direction towards() {
		return TankBattle.getGame().getProcess().send(new PositionPropertyEvent(this)).getTowards();
	}

	default public Positionable setTowards(Direction d) {
		TankBattle.getGame().getProcess()
				.send(new PositionPropertyEvent(this, PositionPropertyEvent.SET_TOWARDS).setTowards(d));
		return this;
	}

	default public int layer() {
		return TankBattle.getGame().getProcess().send(new PositionPropertyEvent(this)).getLayer();
	}

	default public Positionable setLayer(int layer) {
		TankBattle.getGame().getProcess()
				.send(new PositionPropertyEvent(this, PositionPropertyEvent.SET_LAYER).setLayer(layer));
		return this;
	}

	default public double getX() {
		return position().getX();
	}

	default public double getY() {
		return position().getY();
	}

}
