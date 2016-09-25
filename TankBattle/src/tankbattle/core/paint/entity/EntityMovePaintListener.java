package tankbattle.core.paint.entity;

import static java.lang.Math.ceil;

import java.awt.Image;

import tankbattle.core.entity.Entity;
import tankbattle.core.event.EventProcess;
import tankbattle.core.event.Listener;
import tankbattle.core.event.ListenerItem;
import tankbattle.core.move.EntityMoveEvent;
import tankbattle.core.others.ImageUtils;
import tankbattle.core.paint.EntityPaintEvent;
import tankbattle.core.position.Direction;
import tankbattle.core.position.Vector;
import tankbattle.core.view.EntityNode;

/**
 * 移动时会随移动距离改变图像的监听器<br>
 * north、south、east、west分别为在各个方向上的图片数组<br>
 * distance 表示 移动多少距离后改变一次图像,默认值为10<br>
 * swidth、sheight 为图像大小相对于实体的大小的倍数，默认值为1.0，即图像大小与实体大小相同<br>
 * offx、offy 为绘图起点距实体中心的偏差量与图片宽度或高度的比值，默认为-0.5，即图片中心与实体中心重合<br>
 * 
 * @author Gogo
 *
 */
public class EntityMovePaintListener implements Listener<EntityPaintEvent> {

	private Class<? extends Entity> cls;
	private Image[] north;
	private Image[] south;
	private Image[] east;
	private Image[] west;

	private double swidth = 1, sheight = 1;
	private double offx = -0.5, offy = -0.5;

	private int i;

	private int distance = 10;
	private int pass;

	private MoveListener ml;

	public EntityMovePaintListener(Class<? extends Entity> cls) {
		super();
		this.cls = cls;
	}

	public EntityMovePaintListener(Class<? extends Entity> cls, int distance) {
		super();
		this.cls = cls;
		this.distance = distance;
	}

	@Override
	public void init(EventProcess process, ListenerItem<EntityPaintEvent> item) {
		ml = new MoveListener();
		process.addListener(Listener.AFTER_EXECUTE, EntityMoveEvent.class, ml);
	}

	@Override
	public void destory(EventProcess process, ListenerItem<EntityPaintEvent> item) {
		process.removeListener(ml);
	}

	@Override
	public void listen(EntityPaintEvent event) {
		EntityNode node = event.getNode();
		Entity e = event.getPaintable();
		if (event.canceled() || event.executed() || node == null || !cls.isAssignableFrom(e.getClass())) {
			return;
		}
		// 得到要绘制的实际宽高
		int w = (int) ceil(e.shape().boundingRectWidth() * swidth),
				h = (int) ceil(e.shape().boundingRectHeight() * sheight);
		node.setWidth(w).setHeight(h);
		node.setVector(new Vector(w * offx, h * offy));
		Image image = null;
		// 决定方向
		if (e.towards().equals(Direction.NORTH)) {
			image = north[i % north.length];
		} else if (e.towards().equals(Direction.SOUTH)) {
			image = south[i % south.length];
		} else if (e.towards().equals(Direction.EAST)) {
			image = east[i % east.length];
		} else if (e.towards().equals(Direction.WEST)) {
			image = west[i % west.length];
		}
		if (image != null) {
			node.setImage(ImageUtils.copyImage(ImageUtils.toBufferedImage(image)));
			event.setExecuted(true);
		}
	}

	public String getLID() {
		return "EntityMovePaintListener:" + cls.getName();
	}

	/**
	 * 实体移动后自动切换图片，实现动画效果的监听器<br>
	 * 
	 * @author Gogo
	 *
	 */
	private class MoveListener implements Listener<EntityMoveEvent> {

		@Override
		public void listen(EntityMoveEvent event) {
			if (event.executed() && event.getVector() != null && cls.isAssignableFrom(event.getMover().getClass())) {
				pass += event.getVector().length();
				if (pass >= distance) {
					pass = pass % distance;
					i++;
				}
			}
		}

	}

	public Image[] getNorth() {
		return north;
	}

	public EntityMovePaintListener setNorth(Image... north) {
		this.north = north;
		return this;
	}

	public Image[] getSouth() {
		return south;
	}

	public EntityMovePaintListener setSouth(Image... south) {
		this.south = south;
		return this;
	}

	public Image[] getEast() {
		return east;
	}

	public EntityMovePaintListener setEast(Image... east) {
		this.east = east;
		return this;
	}

	public Image[] getWest() {
		return west;
	}

	public EntityMovePaintListener setWest(Image... west) {
		this.west = west;
		return this;
	}

	public Class<? extends Entity> getCls() {
		return cls;
	}

	public int getDistance() {
		return distance;
	}

	public EntityMovePaintListener setDistance(int distance) {
		this.distance = distance;
		return this;
	}

	public double getSwidth() {
		return swidth;
	}

	public EntityMovePaintListener setSwidth(double swidth) {
		this.swidth = swidth;
		return this;
	}

	public double getSheight() {
		return sheight;
	}

	public EntityMovePaintListener setSheight(double sheight) {
		this.sheight = sheight;
		return this;
	}

	public double getOffx() {
		return offx;
	}

	public EntityMovePaintListener setOffx(double offx) {
		this.offx = offx;
		return this;
	}

	public double getOffy() {
		return offy;
	}

	public EntityMovePaintListener setOffy(double offy) {
		this.offy = offy;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cls == null) ? 0 : cls.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityMovePaintListener other = (EntityMovePaintListener) obj;
		if (cls == null) {
			if (other.cls != null)
				return false;
		} else if (!cls.equals(other.cls))
			return false;
		return true;
	}

}
