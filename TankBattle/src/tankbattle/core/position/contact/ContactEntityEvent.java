package tankbattle.core.position.contact;

import tankbattle.core.Entity;
import tankbattle.core.event.Event;

public class ContactEntityEvent extends Event {

	private Entity mover;

	private Entity entity;

	public Entity getMover() {
		return mover;
	}

	public ContactEntityEvent(Entity mover, Entity entity) {
		super();
		this.mover = mover;
		this.entity = entity;
	}

	public ContactEntityEvent setMover(Entity mover) {
		this.mover = mover;
		return this;
	}

	public Entity getEntity() {
		return entity;
	}

	public ContactEntityEvent setEntity(Entity entity) {
		this.entity = entity;
		return this;
	}

}
