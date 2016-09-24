package tankbattle.core.move;

import tankbattle.core.position.Positionable;

/**
 * 本类是{@link Movable}的默认实现方式<br>
 * 
 * @see Movable
 * @author gogo
 *
 */
public class Mover implements Movable {

	public Mover(Positionable poser) {
		if (poser == null) {
			throw new NullPointerException("Mover 的Positionable 为null");
		}
		positioner = poser;
	}

	private final Positionable positioner;
	private double speed;
	private boolean moving;

	@Override
	public Movable mover() {
		return this;
	}

	@Override
	public Positionable positioner() {
		return positioner;
	}

	@Override
	public double speed() {
		return speed;
	}

	@Override
	public Movable setSpeed(double speed) {
		this.speed = speed;
		return this;
	}

	@Override
	public boolean isMoving() {
		return moving;
	}

	@Override
	public Movable setMoving(boolean moving) {
		this.moving = moving;
		return this;
	}

}
