package tankbattle.core.move;

import tankbattle.core.Tank;

public class TankMoveEvent extends EntityMoveEvent {

	public TankMoveEvent(Tank mover) {
		super(mover);
	}

	@Override
	public Tank getMover() {
		return (Tank) super.getMover();
	}

	public TankMoveEvent setMover(Tank mover) {
		return (TankMoveEvent) super.setMover(mover);
	}

}
