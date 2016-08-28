package tankbattle.core.move.contact;

import java.util.Set;

import tankbattle.core.TankBattle;
import tankbattle.core.entity.Entity;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.event.ListenerItem;
import tankbattle.core.move.BulletMoveEvent;
import tankbattle.core.move.EntityMoveEvent;
import tankbattle.core.move.TankMoveEvent;

/**
 * 控制实体接触的监听器，在实体移动时，它会判断实体是否产生接触、离开事件<br>
 * 
 * @author Gogo
 *
 */
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
			// 储存移动前与移动者接触的实体
			event.put(KEY_PREMOVE, TankBattle.getGame().getEntityGroup().getContact(event.getMover().vshape()));
		}

	}

	@Override
	public void listen(EntityMoveEvent event) {
		if (event.canceled() || !event.executed() || event.getMover() == null || event.contains(KEY_CONTACTED)) {
			return;
		}
		@SuppressWarnings("unchecked")
		// 移动前接触的实体
		Set<Entity> pre = event.getObj(KEY_PREMOVE, Set.class);
		if (pre == null) {
			return;
		}
		// 移动后接触的实体
		Set<Entity> post = TankBattle.getGame().getEntityGroup().getContact(event.getMover().vshape());

		// 离开的实体
		pre.stream().filter(f -> !post.contains(f)).forEach(e -> {
			TankBattle.getGame().getProcess().send(new LeaveEntityEvent(event.getMover(), e));
		});
		// 新接触的实体
		post.stream().filter(f -> !pre.contains(f)).forEach(e -> {
			TankBattle.getGame().getProcess().send(new ContactEntityEvent(event.getMover(), e));
		});

		event.put(KEY_CONTACTED, KEY_CONTACTED);

	}

	@Override
	public void init(EventProcess process, ListenerItem<EntityMoveEvent> item) {
		process.addListener(PreMoveListener.LID, Listener.EARLY, EntityMoveEvent.class, new PreMoveListener());
		process.addListener(TankContactListener.LID, 18000, TankMoveEvent.class, new TankContactListener());
		process.addListener(BulletContactListener.LID, 18000, BulletMoveEvent.class, new BulletContactListener());

	}

	/**
	 * 专门用于控制坦克接触的监听器<br>
	 * 
	 * @author Gogo
	 *
	 */
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

	/**
	 * 专门用于控制炮弹接触的监听器<br>
	 * 
	 * @author Gogo
	 *
	 */
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
