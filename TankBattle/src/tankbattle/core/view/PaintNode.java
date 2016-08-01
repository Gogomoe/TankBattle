package tankbattle.core.view;

import java.awt.image.BufferedImage;

import tankbattle.core.paint.Paintable;
import tankbattle.core.position.Point;
/**
 * 表示可以可绘制物的节点<br>
 * <br>
 * 本类中的 point 表示该图片在 View上开始绘制的坐标<br>
 * 需要注意当进行缩放后(View.scale != 1.0 )该坐标会有改变<br>
 */
public class PaintNode {

	private BufferedImage image;

	private Paintable paint;

	private Point point;

	public PaintNode(Paintable paint) {
		super();
		this.paint = paint;
	}

	public BufferedImage getImage() {
		return image;
	}

	public PaintNode setImage(BufferedImage image) {
		this.image = image;
		return this;
	}

	public Paintable getPaint() {
		return paint;
	}

	public Point getPoint() {
		return point;
	}

	public PaintNode setPoint(Point point) {
		this.point = point;
		return this;
	}

}
