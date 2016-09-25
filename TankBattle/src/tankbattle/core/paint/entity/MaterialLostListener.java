package tankbattle.core.paint.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import tankbattle.core.event.Listener;
import tankbattle.core.others.ImageUtils;
import tankbattle.core.paint.EntityPaintEvent;
import tankbattle.core.position.Vector;
import tankbattle.core.view.EntityNode;

/**
 * 材质丢失时绘制的监听器，它会绘制默认材质<br>
 * 
 * @author Gogo
 *
 */
public class MaterialLostListener implements Listener<EntityPaintEvent> {

	public static final String LID = "TankBattle:MaterialLostListener";

	public BufferedImage img;

	public MaterialLostListener() {
		img = new BufferedImage(60, 60, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = img.createGraphics();
		g.setColor(new Color(0, 185, 232, 150));
		g.fillRect(0, 0, 60, 60);
		g.dispose();
	}

	@Override
	public void listen(EntityPaintEvent event) {
		EntityNode node = event.getNode();
		if (event.executed() || event.canceled() || node == null || node.getImage() != null) {
			return;
		}
		// 绘制默认材质
		double w = event.getPaintable().shape().boundingRectWidth(),
				h = event.getPaintable().shape().boundingRectHeight();
		node.setWidth(w).setHeight(h);
		node.setVector(new Vector(-w / 2, -h / 2));
		event.getNode().setImage(ImageUtils.copyImage(img));
		event.setExecuted(true);
	}

}
