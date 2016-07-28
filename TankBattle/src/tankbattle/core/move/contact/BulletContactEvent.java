package tankbattle.core.move.contact;

import tankbattle.core.bullet.Bullet;
import tankbattle.core.entity.Entity;

public class BulletContactEvent extends ContactEntityEvent {

	public BulletContactEvent(Bullet mover, Entity entity) {
		super(mover, entity);
	}

	public Bullet getMover() {
		return (Bullet) super.getMover();
	}

	public BulletContactEvent setMover(Bullet mover) {
		return (BulletContactEvent) super.setMover(mover);
	}

	public BulletContactEvent setEntity(Entity entity) {
		return (BulletContactEvent) super.setEntity(entity);
	}
}
