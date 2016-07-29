package tankbattle.core.view;

import java.awt.image.BufferedImage;

import tankbattle.core.paint.Paintable;
import tankbattle.core.position.Point;

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
