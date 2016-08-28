package tankbattle.core.move.contact;

import tankbattle.core.entity.Entity;
import tankbattle.core.event.Event;
import tankbattle.core.move.collide.CollideEvent;

/**
 * 接触实体事件<br>
 * 本事件会在一个实体开始进入另一个实体时产生<br>
 * 本事件不应该用来判断实体能否通过另一个实体，那么做应该用{@link CollideEvent}
 * 
 * @author Gogo
 *
 */
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
