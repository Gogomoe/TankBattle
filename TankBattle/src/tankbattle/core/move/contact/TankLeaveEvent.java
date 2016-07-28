package tankbattle.core.move.contact;

import tankbattle.core.entity.Entity;
import tankbattle.core.tank.Tank;

public class TankLeaveEvent extends LeaveEntityEvent {

	public TankLeaveEvent(Tank mover, Entity entity) {
		super(mover, entity);
	}

	public Tank getMover() {
		return (Tank) super.getMover();
	}

	public TankLeaveEvent setMover(Tank mover) {
		return (TankLeaveEvent) super.setMover(mover);
	}

	public TankLeaveEvent setEntity(Entity entity) {
		return (TankLeaveEvent) super.setEntity(entity);
	}
}
