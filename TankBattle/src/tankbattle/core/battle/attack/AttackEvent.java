package tankbattle.core.battle.attack;

import tankbattle.core.event.Event;

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
