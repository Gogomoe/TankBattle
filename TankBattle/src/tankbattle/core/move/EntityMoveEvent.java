package tankbattle.core.move;

import tankbattle.core.Entity;

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
