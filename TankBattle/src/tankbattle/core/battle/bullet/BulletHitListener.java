package tankbattle.core.battle.bullet;

import tankbattle.core.Block;
import tankbattle.core.Bullet;
import tankbattle.core.Entity;
import tankbattle.core.event.Listener;
import tankbattle.core.move.contact.BulletContactEvent;

public class BulletHitListener implements Listener<BulletContactEvent> {

	final public static String LID = "TankBattle:BulletHitListener";

	@Override
	public void listen(BulletContactEvent event) {
		if (event.canceled() || event.executed() || event.getMover() == null || event.getEntity() == null) {
			return;
		}
		Bullet bullet = event.getMover();
		Entity entity = event.getEntity();

		if (entity.layer() != bullet.layer()) {
			return;
		}

		if (entity instanceof Block || !bullet.team().equals(entity.team())) {
			bullet.damage(entity);
		}
	}

}
