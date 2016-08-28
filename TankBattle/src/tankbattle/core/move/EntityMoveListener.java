package tankbattle.core.move;

import java.util.Set;
import java.util.stream.Collectors;

import tankbattle.core.TankBattle;
import tankbattle.core.entity.Entity;
import tankbattle.core.event.Listener;
import tankbattle.core.move.collide.CollideEvent;
import tankbattle.core.move.contact.ContactListener;
import tankbattle.core.shape.VectorShape;

public class EntityMoveListener {

	/**
	 * 实体移动后，如果发生碰撞，则在移动前产生碰撞事件的监听器<br>
	 * 详情见 {@link CollideEvent}
	 * 
	 * @author Gogo
	 *
	 */
	public static class ConllidListener implements Listener<EntityMoveEvent> {

		final public static String LID = "TankBattle:EntityMoveListener:ConllidListener";

		@Override
		public void listen(EntityMoveEvent event) {
			if (event.canceled() || event.executed() || event.getMover() == null) {
				return;
			}
			Entity mover = event.getMover();
			@SuppressWarnings("unchecked")
			Set<Entity> pre = event.getObj(ContactListener.KEY_PREMOVE, Set.class);
			VectorShape vshape = mover.vshape();
			Set<Entity> post = TankBattle.getGame().getEntityGroup()
					.getContact(vshape.toVectorShape(vshape.getVector().add(event.getVector())));

			for (Entity entity : post.stream().filter(f -> !pre.contains(f)).collect(Collectors.toSet())) {
				CollideEvent e = TankBattle.getGame().getProcess().send(new CollideEvent(mover, entity));
				if (!e.canPass()) {
					event.setcanceled(true);
					return;
				}
			}
		}

	}

}
