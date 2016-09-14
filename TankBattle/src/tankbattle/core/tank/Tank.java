package tankbattle.core.tank;

import java.util.HashSet;
import java.util.Set;

import tankbattle.core.TankBattle;
import tankbattle.core.battle.attack.Assailable;
import tankbattle.core.battle.tank.BulletFactory;
import tankbattle.core.battle.tank.TankAttackEvent;
import tankbattle.core.bullet.Bullet;
import tankbattle.core.entity.Entity;
import tankbattle.core.move.TankMoveEvent;
import tankbattle.core.paint.TankPaintEvent;
import tankbattle.core.view.View;

/**
 * 表示坦克的类<br>
 * 坦克具有生命，能发动攻击<br>
 * 坦克还支持扩展，extra中可以加入任意属性<br>
 * 
 * @author Gogo
 *
 */
public class Tank extends Entity implements Assailable {

	final public static String KEY_BULLET_FACTORY = "Tank:BulletFactory";

	public void init() {
		super.init();
		BulletFactory bf = () -> {
			Bullet b = new Bullet(this, 20);
			b.setPlayer(this.player());
			b.setTowards(this.towards());
			b.setPosition(this.position());
			Set<Bullet> set = new HashSet<>();
			set.add(b);
			return set;
		};
		put(KEY_BULLET_FACTORY, bf);
	}

	@Override
	public void attack() {
		TankBattle.getGame().getProcess().send(new TankAttackEvent(this));
	}

	@Override
	public void move() {
		TankBattle.getGame().getProcess().send(new TankMoveEvent(this));
	}

	@Override
	public void paint(View view) {
		TankBattle.getGame().getProcess().send(new TankPaintEvent(this, view));
	}
}
