package tankbattle.core.battle.attack;

import tankbattle.core.event.Listener;

/**
 * 伤害属性监听器
 * 
 * @author Gogo
 *
 */
public class DamagePropertyListener implements Listener<DamagePropertyEvent> {

	final public static String LID = "TankBattle:DamagePropertyListener";

	final public static String KEY_ATK = "Damagable:ATK";
	final public static String KEY_ATTACKER = "Damagable:Attacker";

	@Override
	public void listen(DamagePropertyEvent event) {
		if (event.canceled() || event.executed() || event.getDamager() == null) {
			return;
		}
		Damagable damager = event.getDamager();
		int code = event.code();

		// 攻击力
		if ((code & DamagePropertyEvent.SET_ATK) == 0) {
			if (!damager.contains(KEY_ATK)) {
				damager.put(KEY_ATK, 0);
			}
			event.setATK(damager.getInt(KEY_ATK));
		} else {
			damager.put(KEY_ATK, event.getATK());
		}

		// 攻击者
		if ((code & DamagePropertyEvent.SET_ATTACKER) == 0) {
			event.setAttacker(damager.getObj(KEY_ATTACKER, Assailable.class));
		} else {
			damager.put(KEY_ATTACKER, event.getAttacker());
		}
		event.setExecuted(true);
	}

}
