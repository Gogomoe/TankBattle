package tankbattle.core.move;

import tankbattle.core.event.Event;

/**
 * 用于操作与移动有关属性的事件<br>
 * 如Speed(移动速度)，Moving(是否在主动移动)<br>
 * 
 * @author Gogo
 *
 */
public class MovePropertyEvent extends Event {

	final public static int GET_SPEED = 0;
	final public static int GET_MOVING = 0;
	final public static int SET_SPEED = Integer.valueOf("01", 2);
	final public static int SET_MOVING = Integer.valueOf("10", 2);

	private Movable mover;

	private double speed;

	private boolean moving;

	public MovePropertyEvent(Movable mover, int code) {
		super(code);
		this.mover = mover;
	}

	public MovePropertyEvent(Movable mover) {
		super();
		this.mover = mover;
	}

	public Movable getMover() {
		return mover;
	}

	public MovePropertyEvent setMover(Movable mover) {
		this.mover = mover;
		return this;
	}

	public double getSpeed() {
		return speed;
	}

	public MovePropertyEvent setSpeed(double speed) {
		this.speed = speed;
		return this;
	}

	public boolean isMoving() {
		return moving;
	}

	public MovePropertyEvent setMoving(boolean moving) {
		this.moving = moving;
		return this;
	}

}
