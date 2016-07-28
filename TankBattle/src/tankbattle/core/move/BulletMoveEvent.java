package tankbattle.core.move;

import tankbattle.core.bullet.Bullet;

public class BulletMoveEvent extends EntityMoveEvent {

	public BulletMoveEvent(Bullet mover) {
		super(mover);
	}

	@Override
	public Bullet getMover() {
		return (Bullet) super.getMover();
	}

	public BulletMoveEvent setMover(Bullet mover) {
		return (BulletMoveEvent) super.setMover(mover);
	}

}
