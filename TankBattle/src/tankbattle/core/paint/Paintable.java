package tankbattle.core.paint;

import java.util.Map;

import tankbattle.core.TankBattle;
import tankbattle.core.others.Extrable;
import tankbattle.core.view.PaintNode;
import tankbattle.core.view.View;

public interface Paintable extends Extrable {

	final public static String KEY_NODE = "Paintable:PaintNode";

	default public void paint(View view) {
		TankBattle.getGame().getProcess().send(new PaintEvent(this, view));
	}

	default public PaintNode getNode(View view) {
		if (view == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<View, PaintNode> map = this.getObj(KEY_NODE, Map.class);
		if (!map.containsKey(view)) {
			map.put(view, new PaintNode(this));
		}
		return map.get(view);
	}

}
