package tankbattle.core.paint;

import tankbattle.core.event.Event;
import tankbattle.core.view.PaintNode;
import tankbattle.core.view.View;

public class PaintEvent extends Event {

	private Paintable paintable;

	private PaintNode node;

	private View view;

	public PaintEvent(Paintable paintable, View view) {
		super();
		this.paintable = paintable;
		this.node = paintable.getNode();
		this.view = view;
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

	public View getView() {
		return view;
	}

	public PaintEvent setView(View view) {
		this.view = view;
		return this;
	}

}
