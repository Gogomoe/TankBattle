package tankbattle.core.battle.bullet;

import tankbattle.core.TankBattle;
import tankbattle.core.bullet.Bullet;
import tankbattle.core.entity.EntityGroupEvent;
import tankbattle.core.event.Listener;

public class BulletDamageListener implements Listener<BulletDamageEvent> {

	final public static String LID = "TankBattle:BulletDamageListener";

	@Override
	public void listen(BulletDamageEvent event) {
		if (event.canceled() || !event.executed() || event.getDamager() == null) {
			return;
		}
		Bullet bullet = event.getDamager();
		bullet.setLive(false);
		TankBattle.getGame().getProcess().send(new EntityGroupEvent(bullet, EntityGroupEvent.REMOVE_ENTITY));
	}

}
