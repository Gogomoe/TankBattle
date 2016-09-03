package tankbattle.core.view;

import tankbattle.core.input.KeyManager;
import tankbattle.core.position.Point;

public abstract class View {

	protected KeyManager keyManager;

	protected double width, height;
	protected double scale = 1.0;

	protected Point center = new Point(0, 0);

	public View() {
		keyManager = new KeyManager();
	}

	public abstract void paint();

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
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

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public View setKeyManager(KeyManager keyManager) {
		this.keyManager = keyManager;
		return this;
	}

}
