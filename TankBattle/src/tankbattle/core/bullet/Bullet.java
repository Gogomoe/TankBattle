package tankbattle.core.bullet;

import tankbattle.core.TankBattle;
import tankbattle.core.battle.attack.Assailable;
import tankbattle.core.battle.attack.Damagable;
import tankbattle.core.battle.bullet.BulletDamageEvent;
import tankbattle.core.battle.live.Livable;
import tankbattle.core.entity.Entity;
import tankbattle.core.move.BulletMoveEvent;
import tankbattle.core.move.MovePropertyEvent;
import tankbattle.core.paint.BulletPaintEvent;
import tankbattle.core.shape.Rect;
import tankbattle.core.shape.ShapePropertyEvent;
import tankbattle.core.view.View;

/**
 * 炮弹是实现Damagable，没有生命值的实体<br>
 * 大多数炮弹会在接触某些实体后对其造成伤害，然后炮弹本身消失<br>
 * 
 * @author Gogo
 *
 */
public class Bullet extends Entity implements Damagable {

	/**
	 * 默认的炮弹速度
	 */
	public static double BULLET_SPEED = 250;

	public Bullet(Assailable attacker, int ATK) {
		this.setAttacker(attacker).setATK(ATK);
	}

	@Override
	public void init() {
		super.init();
		TankBattle.getGame().getProcess()
				.send(new ShapePropertyEvent(this).setShape(new Rect(20, 20)).setCode(ShapePropertyEvent.SET_SHAPE));
		TankBattle.getGame().getProcess()
				.send(new MovePropertyEvent(this).setSpeed(BULLET_SPEED).setCode(MovePropertyEvent.SET_SPEED));
	}

	@Override
	public void move() {
		TankBattle.getGame().getProcess().send(new BulletMoveEvent(this));
	}

	@Override
	public void damage(Livable target) {
		TankBattle.getGame().getProcess().send(new BulletDamageEvent(this, target));
	}

	@Override
	public void paint(View view) {
		TankBattle.getGame().getProcess().send(new BulletPaintEvent(this, view));
	}

}
