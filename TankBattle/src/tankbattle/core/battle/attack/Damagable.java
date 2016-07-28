package tankbattle.core.battle.attack;

import tankbattle.core.TankBattle;
import tankbattle.core.battle.live.Livable;
import tankbattle.core.others.Extrable;

/**
 * 实现本接口的对象可造成伤害的<br/>
 * 本接口表示的是造成伤害，而不是表示能对敌人发动攻击<br/>
 * 
 * @author Gogo
 *
 */
public interface Damagable extends Extrable {

	default public void damage(Livable target) {
		TankBattle.getGame().getProcess().send(new DamageEvent(this, target));
	}

	default public Assailable getAttacker() {
		return TankBattle.getGame().getProcess().send(new DamagePropertyEvent(this)).getAttacker();
	}

	default public Damagable setAttacker(Assailable attacker) {
		TankBattle.getGame().getProcess()
				.send(new DamagePropertyEvent(this).setAttacker(attacker).setCode(DamagePropertyEvent.SET_ATTACKER));
		return this;
	}

	default public int getATK() {
		return TankBattle.getGame().getProcess().send(new DamagePropertyEvent(this)).getATK();
	}

	default public Damagable setATK(int ATK) {
		TankBattle.getGame().getProcess()
				.send(new DamagePropertyEvent(this).setATK(ATK).setCode(DamagePropertyEvent.SET_ATK));
		return this;
	}
}
