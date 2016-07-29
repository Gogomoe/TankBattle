package tankbattle.core.paint;

import tankbattle.core.event.Listener;
import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;
import tankbattle.core.view.EntityNode;
import tankbattle.core.view.View;

public class EntityPaintListener {

	public static class PointTransListener implements Listener<EntityPaintEvent> {

		final public static String LID = "TankBattle:EntityPaintListener:PointTransListener";

		public void listen(EntityPaintEvent event) {
			EntityNode node = event.getNode();
			View view = event.getView();
			if (event.canceled() || !event.executed() || node == null || view == null || event.getPaintable() == null) {
				return;
			}
			Point pos = event.getPaintable().position().add(node.getVector());
			double scale = view.getScale();
			Point start = view.getCenter().add(new Vector(-view.getScaledWidth() / 2, -view.getScaledHeight() / 2));
			node.setPoint(pos.toVector().subtract(start.toVector()).multiply(1.0 / scale).toPoint());
		}

	}
}
