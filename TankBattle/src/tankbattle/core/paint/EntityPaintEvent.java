package tankbattle.core.paint;

import tankbattle.core.entity.Entity;
import tankbattle.core.view.EntityNode;

public class EntityPaintEvent extends PaintEvent {

	public EntityPaintEvent(Entity paintable) {
		super(paintable);
	}

	@Override
	public Entity getPaintable() {
		return (Entity) super.getPaintable();
	}

	public EntityPaintEvent setPaintable(Entity paintable) {
		return (EntityPaintEvent) super.setPaintable(paintable);
	}

	@Override
	public EntityNode getNode() {
		return (EntityNode) super.getNode();
	}

}
