package tankbattle.core.view;

import tankbattle.core.TankBattle;
import tankbattle.core.position.Point;
import tankbattle.core.shape.Rect;
import tankbattle.core.shape.VRect;

public class View {

	private int width, height;

	private double scale = 1.0;

	private Point center = new Point(0, 0);

	public View(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public void paint() {
		VRect rect = new VRect(new Rect(width, height), center.toVector());
		TankBattle.getGame().getEntityGroup().getAll().stream().filter(p -> {
			double width = p.getNode().getWidth();
			double height = p.getNode().getHeight();
			VRect canvas = new VRect(new Rect(width, height), p.position().add(p.getNode().getVector()).toVector());
			return rect.contacts(canvas);
		}).sorted((e1, e2) -> e1.layer() - e2.layer()).forEach(e -> {
			e.paint(this);
		});
	}

	public int getWidth() {
		return width;
	}

	public View setWidth(int width) {
		this.width = width;
		return this;
	}

	public int getHeight() {
		return height;
	}

	public View setHeight(int height) {
		this.height = height;
		return this;
	}

	public double getScale() {
		return scale;
	}

	public View setScale(double scale) {
		this.scale = scale;
		return this;
	}

	public Point getCenter() {
		return center;
	}

	public View setCenter(Point center) {
		this.center = center;
		return this;
	}

}
