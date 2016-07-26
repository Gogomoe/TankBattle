package tankbattle.core;

import tankbattle.core.battle.attack.Assailable;
import tankbattle.core.battle.attack.Damagable;
import tankbattle.core.battle.bullet.BulletDamageEvent;
import tankbattle.core.battle.live.Livable;
import tankbattle.core.move.BulletMoveEvent;

public class Bullet extends Entity implements Damagable {

	public Bullet(Assailable attacker, int ATK) {
		this.setAttacker(attacker).setATK(ATK);
	}

	public Bullet copy() {
		return new Bullet(this.getAttacker(), this.getATK());
	}

	@Override
	public void move() {
		TankBattle.getGame().getProcess().send(new BulletMoveEvent(this));
	}

	@Override
	public void damage(Livable target) {
		TankBattle.getGame().getProcess().send(new BulletDamageEvent(this, target));
	}

}
