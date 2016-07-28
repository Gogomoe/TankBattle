package tankbattle.core.battle.tank;

import tankbattle.core.battle.attack.AttackEvent;
import tankbattle.core.tank.Tank;

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
