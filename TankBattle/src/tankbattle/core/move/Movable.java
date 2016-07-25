package tankbattle.core.move;

import tankbattle.core.TankBattle;
import tankbattle.core.position.Positionable;

public interface Movable extends Positionable {

	default public double speed() {
		return TankBattle.getGame().getProcess().send(new MovePropertyEvent(this)).getSpeed();
	}

	default public Movable setSpeed(double speed) {
		TankBattle.getGame().getProcess()
				.send(new MovePropertyEvent(this, MovePropertyEvent.SET_SPEED).setSpeed(speed));
		return this;
	}

	default public boolean isMoving() {
		return TankBattle.getGame().getProcess().send(new MovePropertyEvent(this)).isMoving();
	}

	default public Movable setMoving(boolean moving) {
		TankBattle.getGame().getProcess()
				.send(new MovePropertyEvent(this, MovePropertyEvent.SET_MOVING).setMoving(moving));
		return this;
	}

	default public void move() {
		TankBattle.getGame().getProcess().send(new MoveEvent(this));
	}

}
