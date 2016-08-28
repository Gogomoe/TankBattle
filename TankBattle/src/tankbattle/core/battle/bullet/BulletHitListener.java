package tankbattle.core.battle.bullet;

import tankbattle.core.block.Block;
import tankbattle.core.bullet.Bullet;
import tankbattle.core.entity.Entity;
import tankbattle.core.event.Listener;
import tankbattle.core.move.contact.BulletContactEvent;

/**
 * 炮弹与实体接触时的监听器，可能的话会产生对实体造成伤害的事件<br>
 * 
 * @author Gogo
 *
 */
public class BulletHitListener implements Listener<BulletContactEvent> {

	final public static String LID = "TankBattle:BulletHitListener";

	@Override
	public void listen(BulletContactEvent event) {
		if (event.canceled() || event.executed() || event.getMover() == null || event.getEntity() == null) {
			return;
		}

		// 判断炮弹是否能击中该实体
		Bullet bullet = event.getMover();
		Entity entity = event.getEntity();

		if (entity.layer() != bullet.layer() || entity instanceof Bullet) {
			return;
		}

		if (entity instanceof Block || !bullet.team().equals(entity.team())) {
			bullet.damage(entity);
		}
	}

}
