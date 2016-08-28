package tankbattle.core.battle.tank;

import java.util.Set;

import tankbattle.core.bullet.Bullet;

/**
 * 炮弹生成器，用于生成攻击时产生的炮弹
 * @author Gogo
 *
 */
@FunctionalInterface
public interface BulletFactory {

	public Set<Bullet> createBullet();

}
