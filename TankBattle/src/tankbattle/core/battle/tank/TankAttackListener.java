package tankbattle.core.battle.tank;

import tankbattle.core.TankBattle;
import tankbattle.core.entity.EntityGroupEvent;
import tankbattle.core.event.Listener;
import tankbattle.core.tank.Tank;

/**
 * 坦克攻击监听器，默认会获取坦克的炮弹生成器({@link BulletFactory})来产生炮弹
 * 
 * @author Gogo
 *
 */
public class TankAttackListener implements Listener<TankAttackEvent> {

	final public static String LID = "TankBattle:TankAttackListener";

	@Override
	public void listen(TankAttackEvent event) {
		if (event.canceled() || event.executed() || event.getAttacker() == null) {
			return;
		}
		// 使用BulletFactory生成炮弹
		event.getAttacker().getObj(Tank.KEY_BULLET_FACTORY, BulletFactory.class).createBullet().forEach(b -> {
			TankBattle.getGame().getProcess().send(new EntityGroupEvent(b, EntityGroupEvent.ADD_ENTITY));
			b.setMoving(true);
		});
		event.setExecuted(true);
	}

}
