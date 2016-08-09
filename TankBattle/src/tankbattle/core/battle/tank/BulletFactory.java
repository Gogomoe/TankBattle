package tankbattle.core.battle.tank;

import java.util.Set;

import tankbattle.core.bullet.Bullet;

@FunctionalInterface
public interface BulletFactory {

	public Set<Bullet> createBullet();

}
