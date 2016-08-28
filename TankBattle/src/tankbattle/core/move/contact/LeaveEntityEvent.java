package tankbattle.core.move.contact;

import tankbattle.core.entity.Entity;
import tankbattle.core.event.Event;

/**
 * 离开实体事件<br>
 * 本事件会在一个实体离开另一个实体时产生<br>
 * 
 * @author Gogo
 *
 */
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
