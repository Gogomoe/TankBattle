package tankbattle.core.battle.attack;

import tankbattle.core.event.Event;

public class DamagePropertyEvent extends Event {

	final public static int GET_ATK = 0;
	final public static int GET_ATTACKER = 0;

	final public static int SET_ATK = Integer.valueOf("01", 2);
	final public static int SET_ATTACKER = Integer.valueOf("10", 2);

	private Damagable damager;

	private Assailable attacker;
	private int ATK;

	public DamagePropertyEvent(Damagable damager) {
		super();
		this.damager = damager;
	}

	public DamagePropertyEvent(Damagable damager, int code) {
		this.damager = damager;
		this.code = code;
	}

	public Assailable getAttacker() {
		return attacker;
	}

	public DamagePropertyEvent setAttacker(Assailable attacker) {
		this.attacker = attacker;
		return this;
	}

	public int getATK() {
		return ATK;
	}

	public DamagePropertyEvent setATK(int ATK) {
		this.ATK = ATK;
		return this;
	}

	public Damagable getDamager() {
		return damager;
	}

	public DamagePropertyEvent setDamager(Damagable damager) {
		this.damager = damager;
		return this;
	}

}
