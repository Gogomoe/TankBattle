package tankbattle.core.attack;

import tankbattle.core.Tank;

public class TankAttackEvent extends AttackEvent {
	private Tank attacker;

	public TankAttackEvent(Tank attacker) {
		super(attacker);
		this.attacker = attacker;
	}

	public Tank getAttacker() {
		return attacker;
	}

	public TankAttackEvent setAttacker(Tank attacker) {
		this.attacker = attacker;
		return this;
	}

}
