package tankbattle.core.battle.attack;

import tankbattle.core.event.Event;

/**
 * 攻击后开始攻击冷却的事件
 * @author Gogo
 *
 */
public class AttackCoolDownEvent extends Event {

	private Assailable attacker;

	private int time;

	public AttackCoolDownEvent(Assailable attacker) {
		super();
		this.attacker = attacker;
	}

	public AttackCoolDownEvent(Assailable attacker, int time) {
		super();
		this.attacker = attacker;
		this.time = time;
	}

	public Assailable getAttacker() {
		return attacker;
	}

	public AttackCoolDownEvent setAttacker(Assailable attacker) {
		this.attacker = attacker;
		return this;
	}

	public int getTime() {
		return time;
	}

	public AttackCoolDownEvent setTime(int time) {
		this.time = time;
		return this;
	}

}
