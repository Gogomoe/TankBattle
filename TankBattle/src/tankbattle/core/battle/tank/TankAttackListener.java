package tankbattle.core.battle.tank;

import tankbattle.core.Tank;
import tankbattle.core.TankBattle;
import tankbattle.core.event.Listener;

public class TankAttackListener implements Listener<TankAttackEvent> {

	final public static String LID = "TankBattle:TankAttackListener";

	@Override
	public void listen(TankAttackEvent event) {
		if (event.canceled() || event.executed() || event.getAttacker() == null) {
			return;
		}
		event.getAttacker().getObj(Tank.KEY_BULLET_FACTORY, BulletFactory.class).createBullet().forEach(b -> {
			TankBattle.getGame().getEntityGroup().add(b);
			b.setMoving(true);
		});
		event.setExecuted(true);
	}

}
