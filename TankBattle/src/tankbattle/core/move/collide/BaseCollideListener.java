package tankbattle.core.move.collide;

import tankbattle.core.bullet.Bullet;
import tankbattle.core.entity.Entity;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.event.ListenerItem;

/**
 * 基本的用于处理碰撞的监听器<br>
 * 判断实体能从哪些实体上经过<br>
 * 
 * @author Gogo
 *
 */
public class BaseCollideListener implements Listener<CollideEvent> {

	final public static String LID = "TankBattle:BaseCollideListener";

	@Override
	public void listen(CollideEvent event) {
		if (event.canceled() || event.executed() || event.getMover() == null || event.getEntity() == null) {
			return;
		}
		// 判断实体能否经过
		Entity mover = event.getMover();
		Entity entity = event.getEntity();
		event.setPass(mover.layer() != entity.layer());
		event.setExecuted(true);
	}

	@Override
	public void init(EventProcess process, ListenerItem<CollideEvent> item) {
		process.addListener(BulletCollideListener.LID, Listener.NORMAL, CollideEvent.class,
				new BulletCollideListener());

	}

	/**
	 * 针对炮弹({@link Bullet})类的碰撞监听器<br>
	 * 
	 * @author Gogo
	 *
	 */
	public static class BulletCollideListener implements Listener<CollideEvent> {

		final public static String LID = "TankBattle:BaseCollideListener:BulletCollideListener";

		@Override
		public void listen(CollideEvent event) {
			Entity mover = event.getMover();
			if (event.canceled() || event.executed() || event.getMover() == null || mover == null) {
				return;
			}
			if (!(mover instanceof Bullet)) {
				return;
			}
			event.setPass(true);
			event.setExecuted(true);
		}

	}

}
