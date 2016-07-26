package tankbattle.core.battle.tank;

import java.util.HashSet;
import java.util.Set;

import tankbattle.core.Bullet;
import tankbattle.core.Entity;

public interface BulletFactory {

	public Set<Bullet> createBullet();

	class EntityBulletFactory implements BulletFactory {

		private Entity entity;
		private Bullet bullet;

		public EntityBulletFactory(Entity entity, Bullet bullet) {
			super();
			this.entity = entity;
			this.bullet = bullet;
		}

		@Override
		public Set<Bullet> createBullet() {
			Set<Bullet> set = new HashSet<>();
			Bullet b = bullet.copy();
			b.setPlayer(entity.player());
			b.setTowards(entity.towards());
			set.add(b);
			return set;
		}

	}

}
