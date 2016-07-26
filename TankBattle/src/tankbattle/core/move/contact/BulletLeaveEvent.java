package tankbattle.core.move.contact;

import tankbattle.core.Bullet;
import tankbattle.core.Entity;

public class BulletLeaveEvent extends LeaveEntityEvent {

	public BulletLeaveEvent(Bullet mover, Entity entity) {
		super(mover, entity);
	}

	public Bullet getMover() {
		return (Bullet) super.getMover();
	}

	public BulletLeaveEvent setMover(Bullet mover) {
		return (BulletLeaveEvent) super.setMover(mover);
	}

	public BulletLeaveEvent setEntity(Entity entity) {
		return (BulletLeaveEvent) super.setEntity(entity);
	}
}
