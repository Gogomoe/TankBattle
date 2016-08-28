package tankbattle.core.battle.bullet;

import tankbattle.core.TankBattle;
import tankbattle.core.bullet.Bullet;
import tankbattle.core.entity.EntityGroupEvent;
import tankbattle.core.event.Listener;

/**
 * 炮弹造成伤害的监听器
 * 
 * @author Gogo
 *
 */
public class BulletDamageListener implements Listener<BulletDamageEvent> {

	final public static String LID = "TankBattle:BulletDamageListener";

	@Override
	public void listen(BulletDamageEvent event) {
		if (event.canceled() || !event.executed() || event.getDamager() == null) {
			return;
		}
		// 炮弹造成伤害后移除该炮弹
		Bullet bullet = event.getDamager();
		bullet.setLive(false);
		TankBattle.getGame().getProcess().send(new EntityGroupEvent(bullet, EntityGroupEvent.REMOVE_ENTITY));
	}

}
