package tankbattle.core.battle.attack;

import tankbattle.core.event.Listener;

public class DamageListener implements Listener<DamageEvent> {

	final public static String LID = "TankBattle:DamageListener";

	@Override
	public void listen(DamageEvent event) {
		if (event.canceled() || event.executed() || event.getDamager() == null || event.getTarget() == null) {
			return;
		}
		int damage = event.getATK() - event.getDEF() >= 0 ? event.getATK() - event.getDEF() : 0;
		event.getTarget().setHP(event.getTarget().getHP() - (damage));
		event.setExecuted(true);
	}

}
