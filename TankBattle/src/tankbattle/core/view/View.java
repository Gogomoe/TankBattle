package tankbattle.core.view;

import static java.lang.Math.ceil;
import static java.lang.Math.round;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import tankbattle.core.TankBattle;
import tankbattle.core.input.KeyManager;
import tankbattle.core.position.Point;
import tankbattle.core.shape.Rect;
import tankbattle.core.shape.VRect;

/**
 * 一个视图对象<br>
 * 内部有个JavaFX的Canvas对象，因此可被直接添加到窗体上<br>
 * 具有控制键盘的KeyManager来进行按键控制<br>
 * scale属性表示窗体显示的内容与窗体大小的比值，数字越大显示的内容越多<br>
 * 
 * @author Gogo
 *
 */
public class View {

	private Canvas canvas;

	private KeyManager keyManager;

	private double scale = 1.0;

	private Point center = new Point(0, 0);

	public View(int width, int height) {
		super();
		canvas = new Canvas(width, height);
		keyManager = new KeyManager();
	}

	public void paint() {
		// 窗体在屏幕上的位置
		VRect rect = new VRect(new Rect(getWidth(), getHeight()), center.toVector());
		BufferedImage img = new BufferedImage((int) ceil(getWidth()), (int) ceil(getHeight()),
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = img.createGraphics();
		TankBattle.getGame().getEntityGroup().getAll().stream().filter(p -> {
			// 过滤没显示在屏幕上的节点
			EntityNode node = p.getNode(this);
			if (node.getVector() == null) {
				p.paint(this);
			}
			// 节点在屏幕上的位置
			VRect canvas = new VRect(new Rect(node.getWidth(), node.getHeight()),
					p.position().add(node.getVector()).toVector());
			return rect.contacts(canvas);
		}).sorted((e1, e2) -> {
			// 进行排序，从低层、上方、左边的图像先被绘制
			int l = e1.layer() - e2.layer();
			if (l == 0) {
				int y = (int) (e1.getY() - e2.getY());
				if (y == 0) {
					return (int) (e1.getX() - e2.getX());
				}
				return y;
			}
			return l;
		}).forEach(e -> {
			// 绘制图像
			EntityNode node = e.getNode(this);
			e.paint(this);
			if (node.getImage() != null) {
				g.drawImage(node.getImage(), round((float) node.getPoint().getX()),
						round((float) node.getPoint().getY()), null);
			}
		});
		g.dispose();
		WritableImage wi = SwingFXUtils.toFXImage(img, null);
		// 清除上次的绘制
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

	/**
	 * 表示屏幕在地图上的宽度
	 * 
	 * @return
	 */
	public double getScaledWidth() {
		return getWidth() * scale;
	}

	/**
	 * 表示屏幕在地图上的高度
	 * 
	 * @return
	 */
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

	public KeyManager getKeyManager() {
		return keyManager;
	}

}
