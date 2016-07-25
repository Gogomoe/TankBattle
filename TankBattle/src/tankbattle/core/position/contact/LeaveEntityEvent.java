package tankbattle.core.position.contact;

import tankbattle.core.Entity;
import tankbattle.core.event.Event;

public class LeaveEntityEvent extends Event {

	private Entity mover;

	private Entity entity;

	public Entity getMover() {
		return mover;
	}

	public LeaveEntityEvent(Entity mover, Entity entity) {
		super();
		this.mover = mover;
		this.entity = entity;
	}

	public LeaveEntityEvent setMover(Entity mover) {
		this.mover = mover;
		return this;
	}

	public Entity getEntity() {
		return entity;
	}

	public LeaveEntityEvent setEntity(Entity entity) {
		this.entity = entity;
		return this;
	}

}
