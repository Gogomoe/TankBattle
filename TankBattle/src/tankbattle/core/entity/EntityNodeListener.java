package tankbattle.core.entity;

import tankbattle.core.TankBattle;
import tankbattle.core.event.Listener;
import tankbattle.core.view.EntityNode;

public class EntityNodeListener implements Listener<EntityGroupEvent> {

	final public static String LID = "TankBattle:EntityNodeListener";

	@Override
	public void listen(EntityGroupEvent event) {
		if (event.canceled() || !event.executed() || event.getEntity() == null) {
			return;
		}
		if (event.code() == 0) {
			TankBattle.getGame().getPaints().put(event.getEntity(), new EntityNode(event.getEntity()));
		} else if (event.code() == 1) {
			TankBattle.getGame().getPaints().remove(event.getEntity());
		}
	}

}
