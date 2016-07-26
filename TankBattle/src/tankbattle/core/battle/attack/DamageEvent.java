package tankbattle.core.battle.attack;

import tankbattle.core.battle.live.Livable;
import tankbattle.core.event.Event;

public class DamageEvent extends Event {

	private Damagable damager;

	private Livable target;

	private int ATK, DEF;

	public DamageEvent(Damagable damager, Livable target) {
		super();
		this.damager = damager;
		this.target = target;
		ATK = damager.getATK();
		DEF = target.getDEF();
	}

	public Damagable getDamager() {
		return damager;
	}

	public DamageEvent setDamager(Damagable damager) {
		this.damager = damager;
		return this;
	}

	public Livable getTarget() {
		return target;
	}

	public DamageEvent setTarget(Livable target) {
		this.target = target;
		return this;
	}

	public int getATK() {
		return ATK;
	}

	public DamageEvent setATK(int ATK) {
		this.ATK = ATK;
		return this;
	}

	public int getDEF() {
		return DEF;
	}

	public DamageEvent setDEF(int DEF) {
		this.DEF = DEF;
		return this;
	}

}
