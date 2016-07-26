package tankbattle.core.battle.bullet;

import tankbattle.core.Bullet;
import tankbattle.core.battle.attack.DamageEvent;
import tankbattle.core.battle.live.Livable;

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
