package tankbattle.core.entity;

import tankbattle.core.TankBattle;
import tankbattle.core.event.Listener;

public class EntityGroupListener implements Listener<EntityGroupEvent> {

	final public static String LID = "TankBattle:EntityGroupListener";

	@Override
	public void listen(EntityGroupEvent event) {
		if (event.canceled() || event.executed() || event.getEntity() == null) {
			return;
		}
		if (event.code() == 0) {
			TankBattle.getGame().getEntityGroup().add(event.getEntity());
		} else if (event.code() == 1) {
			TankBattle.getGame().getEntityGroup().remove(event.getEntity());
		}
		event.setExecuted(true);
	}

}
