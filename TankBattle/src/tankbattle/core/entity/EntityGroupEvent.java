package tankbattle.core.entity;

import tankbattle.core.event.Event;

/**
 * 向游戏添加或删除实体的事件<br>
 * 通常情况下不要使用EntityGroup.add()或remove()来添加删除实体<br>
 * 而应该使用本事件，因为有些监听器可能会进行一些操作<br>
 * 
 * @author Gogo
 *
 */
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
