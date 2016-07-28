package tankbattle.core.view;

import javafx.scene.canvas.Canvas;
import tankbattle.core.paint.Paintable;
import tankbattle.core.position.Point;

public class PaintNode {

	private Canvas canvas = new Canvas();

	private Paintable paint;

	private Point point;

	public PaintNode(Paintable paint) {
		super();
		this.paint = paint;
	}

	public Canvas getCanvas() {
		return canvas;
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
