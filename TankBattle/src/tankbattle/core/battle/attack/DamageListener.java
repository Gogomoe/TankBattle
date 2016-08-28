package tankbattle.core.battle.attack;

import tankbattle.core.event.Listener;

/**
 * 造成伤害监听器
 * 
 * @author Gogo
 *
 */
public class DamageListener implements Listener<DamageEvent> {

	final public static String LID = "TankBattle:DamageListener";

	@Override
	public void listen(DamageEvent event) {
		if (event.canceled() || event.executed() || event.getDamager() == null || event.getTarget() == null) {
			return;
		}
		// 计算伤害，并减少生命
		int damage = event.getATK() - event.getDEF() >= 0 ? event.getATK() - event.getDEF() : 0;
		event.getTarget().setHP(event.getTarget().getHP() - (damage));
		event.setExecuted(true);
	}

}
