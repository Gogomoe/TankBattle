package tankbattle.core.tank;

import tankbattle.core.TankBattle;
import tankbattle.core.battle.attack.Assailable;
import tankbattle.core.battle.tank.BulletFactory;
import tankbattle.core.battle.tank.TankAttackEvent;
import tankbattle.core.bullet.Bullet;
import tankbattle.core.entity.Entity;
import tankbattle.core.move.TankMoveEvent;
import tankbattle.core.paint.TankPaintEvent;

/**
 * 表示坦克的类<br/>
 * 坦克具有生命，能发动攻击<br/>
 * 坦克还支持扩展，extra中可以加入任意属性<br/>
 * 
 * @author Gogo
 *
 */
public class Tank extends Entity implements Assailable {

	final public static String KEY_BULLET_FACTORY = "Tank:BulletFactory";

	{
		put(KEY_BULLET_FACTORY, new BulletFactory.EntityBulletFactory(this, new Bullet(this, 20)));
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
	public void paint() {
		TankBattle.getGame().getProcess().send(new TankPaintEvent(this));
	}
}
