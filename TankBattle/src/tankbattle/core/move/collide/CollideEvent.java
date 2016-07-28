package tankbattle.core.move.collide;

import tankbattle.core.entity.Entity;
import tankbattle.core.event.Event;

public class CollideEvent extends Event {

	private Entity mover;

	private Entity entity;

	private boolean pass;

	public CollideEvent(Entity mover, Entity entity) {
		super();
		this.mover = mover;
		this.entity = entity;
	}

	public Entity getMover() {
		return mover;
	}

	public CollideEvent setMover(Entity mover) {
		this.mover = mover;
		return this;
	}

	public Entity getEntity() {
		return entity;
	}

	public CollideEvent setEntity(Entity entity) {
		this.entity = entity;
		return this;
	}

	public boolean canPass() {
		return pass;
	}

	public CollideEvent setPass(boolean pass) {
		this.pass = pass;
		return this;
	}

}
