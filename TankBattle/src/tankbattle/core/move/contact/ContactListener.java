package tankbattle.core.move.contact;

import java.util.Set;

import tankbattle.core.Entity;
import tankbattle.core.TankBattle;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.move.EntityMoveEvent;

public class ContactListener implements Listener<EntityMoveEvent> {

	final public static String LID = "TankBattle:ContactListener";

	final public static String KEY_PREMOVE = "ContactListener:PreEntitySet";

	public class PreMoveListener implements Listener<EntityMoveEvent> {

		final public static String LID = "TankBattle:ContactListener:PreMoveListener";

		@Override
		public void listen(EntityMoveEvent event) {
			if (event.canceled() || event.executed() || event.getMover() == null) {
				return;
			}
			event.put(KEY_PREMOVE, TankBattle.getGame().getEntityGroup().getContact(event.getMover().vshape()));
		}

	}

	@Override
	public void listen(EntityMoveEvent event) {
		if (event.canceled() || !event.executed() || event.getMover() == null) {
			return;
		}
		@SuppressWarnings("unchecked")
		Set<Entity> pre = event.getObj(KEY_PREMOVE, Set.class);
		if (pre == null) {
			return;
		}
		Set<Entity> post = TankBattle.getGame().getEntityGroup().getContact(event.getMover().vshape());

		pre.stream().filter(f -> !post.contains(f)).forEach(e -> {
			TankBattle.getGame().getProcess().send(new LeaveEntityEvent(event.getMover(), e));
		});
		post.stream().filter(f -> !pre.contains(f)).forEach(e -> {
			TankBattle.getGame().getProcess().send(new ContactEntityEvent(event.getMover(), e));
		});

	}

	@Override
	public void init(EventProcess process) {
		process.addListener(PreMoveListener.LID, Listener.EARLY, EntityMoveEvent.class, new PreMoveListener());
	}

}
