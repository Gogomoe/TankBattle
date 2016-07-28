package tankbattle.core.battle.bullet;

import tankbattle.core.battle.attack.DamageEvent;
import tankbattle.core.battle.live.Livable;
import tankbattle.core.bullet.Bullet;

public class BulletDamageEvent extends DamageEvent {

	public BulletDamageEvent(Bullet damager, Livable target) {
		super(damager, target);
	}

	@Override
	public Bullet getDamager() {
		return (Bullet) super.getDamager();
	}

	public BulletDamageEvent setDamager(Bullet damager) {
		return (BulletDamageEvent) super.setDamager(damager);
	}

}
