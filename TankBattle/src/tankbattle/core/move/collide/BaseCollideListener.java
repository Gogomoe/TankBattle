package tankbattle.core.move.collide;

import tankbattle.core.Entity;
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

}
