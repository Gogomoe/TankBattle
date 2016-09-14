package tankbattle.core.paint.entity;

import static java.lang.Math.ceil;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import tankbattle.core.event.Listener;
import tankbattle.core.paint.EntityPaintEvent;
import tankbattle.core.position.Point;
import tankbattle.core.position.Vector;
import tankbattle.core.view.EntityNode;
import tankbattle.core.view.View;

/**
 * 用于实体位置转换的监听器，能将EntityNode中表示偏差量的Vector转换为PaintNode中表示位置的Point<br>
 * 
 * @author Gogo
 *
 */
public class PointTransListener implements Listener<EntityPaintEvent> {

	final public static String LID = "TankBattle:PointTransListener";

	public void listen(EntityPaintEvent event) {
		EntityNode node = event.getNode();
		View view = event.getView();
		if (event.canceled() || !event.executed() || node == null || view == null || event.getPaintable() == null) {
			return;
		}
		// 地图上的起始点
		Point pos = event.getPaintable().position().add(node.getVector());
		double scale = view.getScale();
		// 屏幕显示范围在地图上对应的起始点
		Point start = view.getCenter().add(new Vector(-view.getScaledWidth() / 2, -view.getScaledHeight() / 2));
		node.setPoint(pos.toVector().subtract(start.toVector()).multiply(1.0 / scale).toPoint());
		BufferedImage img = node.getImage();
		// 屏幕上应绘制的图片大小
		int swidth = (int) ceil(node.getWidth() / scale), sheight = (int) ceil(node.getHeight() / scale);
		BufferedImage simg = new BufferedImage(swidth, sheight, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = simg.createGraphics();
		g.drawImage(img, 0, 0, swidth, sheight, null);
		g.dispose();
		node.setImage(simg);
	}

}
