package tankbattle.core.move;

import tankbattle.core.TankBattle;
import tankbattle.core.others.Extrable;
import tankbattle.core.position.Positionable;

/**
 * 表示对象可以移动，也以为着对象一定是有位置的({@link Positionable})<br>
 * <br>
 * 本类具有默认实现方式{@link Mover}，实现本类可以参见{@link Extrable}的实现方式<br>
 * 
 * @see Mover
 * @author Gogo
 *
 */
public interface Movable {

	public Movable mover();

	public Positionable positioner();

	default public double speed() {
		return mover().speed();
	}

	default public Movable setSpeed(double speed) {
		return mover().setSpeed(speed);
	}

	default public boolean isMoving() {
		return mover().isMoving();
	}

	default public Movable setMoving(boolean moving) {
		return mover().setMoving(moving);
	}

	default public void move() {
		TankBattle.getGame().getProcess().send(new MoveEvent(this));
	}

}
