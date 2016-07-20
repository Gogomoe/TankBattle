package tankbattle.core;

import tankbattle.core.attack.Assailable;
import tankbattle.core.attack.Damagable;

public class Bullet extends Entity implements Damagable {

	public Bullet(Assailable attacker, int ATK) {
		this.setAttacker(attacker).setATK(ATK);
	}

	public Bullet copy() {
		return (Bullet) new Bullet(this.getAttacker(), this.getATK());
	}

}
