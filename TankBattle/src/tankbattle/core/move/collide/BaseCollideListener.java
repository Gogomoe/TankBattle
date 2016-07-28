package tankbattle.core.move.collide;

import tankbattle.core.bullet.Bullet;
import tankbattle.core.entity.Entity;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;

public class BaseCollideListener implements Listener<CollideEvent> {

	final public static String LID = "TankBattle:BaseCollideListener";

	@Override
	public void listen(CollideEvent event) {
		if (event.canceled() || event.executed() || event.getMover() == null || event.getEntity() == null) {
			return;
		}
		Entity mover = event.getMover();
		Entity entity = event.getEntity();
		event.setPass(mover.layer() != entity.layer());
		event.setExecuted(true);
	}

	@Override
	public void init(EventProcess process) {
		process.addListener(BulletCollideListener.LID, Listener.NORMAL, CollideEvent.class,
				new BulletCollideListener());

	}

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
