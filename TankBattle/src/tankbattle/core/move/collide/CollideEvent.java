package tankbattle.core.move.collide;

import tankbattle.core.entity.Entity;
import tankbattle.core.event.Event;
import tankbattle.core.move.contact.ContactEntityEvent;

/**
 * 本事件会在一个实体即将于另一个实体碰撞时产生<br>
 * <br>
 * 它与 {@link ContactEntityEvent}的不同之处在于，
 * 本事件是碰撞发生前产生的，ContactEntityEvent则是已经移动接触时产生的<br>
 * <br>
 * 本事件通常用于处理判断一个实体能否通过另一个实体，比如判断坦克能否从河流上经过<br>
 * 
 * @author Gogo
 *
 */
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
