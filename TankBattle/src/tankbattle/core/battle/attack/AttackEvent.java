package tankbattle.core.battle.attack;

import tankbattle.core.event.Event;

/**
 * 攻击事件
 * @author Gogo
 *
 */
public class AttackEvent extends Event {

	private Assailable attacker;

	public AttackEvent(Assailable attacker) {
		super();
		this.attacker = attacker;
	}

	public Assailable getAttacker() {
		return attacker;
	}

	public AttackEvent setAttacker(Assailable attacker) {
		this.attacker = attacker;
		return this;
	}

}
