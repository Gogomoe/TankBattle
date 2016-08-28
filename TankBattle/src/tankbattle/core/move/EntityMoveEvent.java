package tankbattle.core.move;

import tankbattle.core.entity.Entity;

/**
 * 实体移动事件，会在实体移动时被发送<br>
 * 
 * @author Gogo
 *
 */
public class EntityMoveEvent extends MoveEvent {

	public EntityMoveEvent(Entity mover) {
		super(mover);
	}

	@Override
	public Entity getMover() {
		return (Entity) super.getMover();
	}

	public EntityMoveEvent setMover(Entity mover) {
		return (EntityMoveEvent) super.setMover(mover);
	}

}
