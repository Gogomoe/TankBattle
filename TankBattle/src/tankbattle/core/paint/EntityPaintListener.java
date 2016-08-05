package tankbattle.core.paint;

import static java.lang.Math.ceil;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
			BufferedImage img = node.getImage();

			if (Math.abs(scale - 1) < 0.01 || img == null) {// 0.99<scale<1.01
				return;
			}
			int swidth = (int) ceil(node.getWidth() / scale), sheight = (int) ceil(node.getHeight() / scale);
			BufferedImage simg = new BufferedImage(swidth, sheight, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g = simg.createGraphics();
			g.drawImage(img, 0, 0, swidth, sheight, null);
			g.dispose();
			node.setImage(simg);
		}

	}
}
