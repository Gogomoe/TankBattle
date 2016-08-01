package tankbattle.core.view;

import static java.lang.Math.ceil;
import static java.lang.Math.round;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import tankbattle.core.TankBattle;
import tankbattle.core.position.Point;
import tankbattle.core.shape.Rect;
import tankbattle.core.shape.VRect;

public class View {

	private Canvas canvas;

	private double scale = 1.0;

	private Point center = new Point(0, 0);

	public View(int width, int height) {
		super();
		canvas = new Canvas(width, height);
	}

	public void paint() {
		VRect rect = new VRect(new Rect(getWidth(), getHeight()), center.toVector());
		BufferedImage img = new BufferedImage((int) ceil(getWidth()), (int) ceil(getHeight()),
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = img.createGraphics();
		TankBattle.getGame().getEntityGroup().getAll().stream().filter(p -> {
			EntityNode node = p.getNode(this);
			if (node.getVector() == null) {
				p.paint(this);
			}
			VRect canvas = new VRect(new Rect(node.getWidth(), node.getHeight()),
					p.position().add(node.getVector()).toVector());
			return rect.contacts(canvas);
		}).sorted((e1, e2) -> e1.layer() - e2.layer()).forEach(e -> {
			EntityNode node = e.getNode(this);
			e.paint(this);
			g.drawImage(node.getImage(), round((float) node.getPoint().getX()), round((float) node.getPoint().getY()),
					null);
		});
		g.dispose();
		WritableImage wi = SwingFXUtils.toFXImage(img, null);
		canvas.getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
		canvas.getGraphicsContext2D().drawImage(wi, 0, 0);
	}

	public double getWidth() {
		return canvas.getWidth();
	}

	public double getHeight() {
		return canvas.getHeight();
	}

	public void setWidth(double width) {
		canvas.setWidth(width);
	}

	public void setHeight(double height) {
		canvas.setHeight(height);
	}

	public double getScaledWidth() {
		return getWidth() * scale;
	}

	public double getScaledHeight() {
		return getHeight() * scale;
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

	public Canvas getCanvas() {
		return canvas;
	}

}
