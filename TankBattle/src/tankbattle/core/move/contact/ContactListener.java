package tankbattle.core.move.contact;

import java.util.Set;

import tankbattle.core.TankBattle;
import tankbattle.core.entity.Entity;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.move.BulletMoveEvent;
import tankbattle.core.move.EntityMoveEvent;
import tankbattle.core.move.TankMoveEvent;

public class ContactListener implements Listener<EntityMoveEvent> {

	final public static String LID = "TankBattle:ContactListener";

	final public static String KEY_PREMOVE = "ContactListener:PreEntitySet";

	final public static String KEY_CONTACTED = "ContactListener:Contacted";

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
		if (event.canceled() || !event.executed() || event.getMover() == null || event.contains(KEY_CONTACTED)) {
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

		event.put(KEY_CONTACTED, KEY_CONTACTED);

	}

	@Override
	public void init(EventProcess process) {
		process.addListener(PreMoveListener.LID, Listener.EARLY, EntityMoveEvent.class, new PreMoveListener());
		process.addListener(TankContactListener.LID, 18000, TankMoveEvent.class, new TankContactListener());
		process.addListener(BulletContactListener.LID, 18000, BulletMoveEvent.class, new BulletContactListener());

	}

	public static class TankContactListener implements Listener<TankMoveEvent> {

		final public static String LID = "TankBattle:ContactListener:TankContactListener";

		@Override
		public void listen(TankMoveEvent event) {
			if (event.canceled() || !event.executed() || event.getMover() == null || event.contains(KEY_CONTACTED)) {
				return;
			}
			@SuppressWarnings("unchecked")
			Set<Entity> pre = event.getObj(KEY_PREMOVE, Set.class);
			if (pre == null) {
				return;
			}
			Set<Entity> post = TankBattle.getGame().getEntityGroup().getContact(event.getMover().vshape());

			pre.stream().filter(f -> !post.contains(f)).forEach(e -> {
				TankBattle.getGame().getProcess().send(new TankLeaveEvent(event.getMover(), e));
			});
			post.stream().filter(f -> !pre.contains(f)).forEach(e -> {
				TankBattle.getGame().getProcess().send(new TankContactEvent(event.getMover(), e));
			});

			event.put(KEY_CONTACTED, KEY_CONTACTED);
		}

	}

	public static class BulletContactListener implements Listener<BulletMoveEvent> {

		final public static String LID = "TankBattle:ContactListener:TankContactListener";

		@Override
		public void listen(BulletMoveEvent event) {
			if (event.canceled() || !event.executed() || event.getMover() == null || event.contains(KEY_CONTACTED)) {
				return;
			}
			@SuppressWarnings("unchecked")
			Set<Entity> pre = event.getObj(KEY_PREMOVE, Set.class);
			if (pre == null) {
				return;
			}
			Set<Entity> post = TankBattle.getGame().getEntityGroup().getContact(event.getMover().vshape());

			pre.stream().filter(f -> !post.contains(f)).forEach(e -> {
				TankBattle.getGame().getProcess().send(new BulletLeaveEvent(event.getMover(), e));
			});
			post.stream().filter(f -> !pre.contains(f)).forEach(e -> {
				TankBattle.getGame().getProcess().send(new BulletContactEvent(event.getMover(), e));
			});

			event.put(KEY_CONTACTED, KEY_CONTACTED);
		}

	}

}
