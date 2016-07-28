package tankbattle.core.paint;

import tankbattle.core.TankBattle;
import tankbattle.core.others.Extrable;
import tankbattle.core.view.PaintNode;
import tankbattle.core.view.View;

public interface Paintable extends Extrable {

	final public static String KEY_NODE = "Paintable:PaintNode";

	default public void paint(View view) {
		TankBattle.getGame().getProcess().send(new PaintEvent(this, view));
	}

	default public PaintNode getNode() {
		return this.getObj(KEY_NODE, PaintNode.class);
	}

}
