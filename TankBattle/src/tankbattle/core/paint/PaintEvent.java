package tankbattle.core.paint;

import tankbattle.core.TankBattle;
import tankbattle.core.event.Event;
import tankbattle.core.view.PaintNode;

public class PaintEvent extends Event {

	private Paintable paintable;

	private PaintNode node;

	public PaintEvent(Paintable paintable) {
		super();
		this.paintable = paintable;
		this.node = TankBattle.getGame().getPaints().get(paintable);
	}

	public Paintable getPaintable() {
		return paintable;
	}

	public PaintEvent setPaintable(Paintable paintable) {
		this.paintable = paintable;
		return this;
	}

	public PaintNode getNode() {
		return node;
	}

	public PaintEvent setNode(PaintNode node) {
		this.node = node;
		return this;
	}

}
