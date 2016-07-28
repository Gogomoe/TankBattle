package tankbattle.core.entity;

import tankbattle.core.event.Event;

public class EntityGroupEvent extends Event {

	final public static int ADD_ENTITY = 0;
	final public static int REMOVE_ENTITY = 1;

	private Entity entity;

	public EntityGroupEvent(Entity entity, int code) {
		super(code);
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}

	public EntityGroupEvent setEntity(Entity entity) {
		this.entity = entity;
		return this;
	}

}
