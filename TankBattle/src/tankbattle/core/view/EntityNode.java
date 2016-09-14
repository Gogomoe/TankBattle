package tankbattle.core.view;

import tankbattle.core.entity.Entity;
import tankbattle.core.position.Vector;

/**
 * 描述一个实体在视图上的节点<br>
 * <br>
 * 本类中的Vector表示要绘制的图片的左上角 距实体坐标(中心)的偏差<br>
 * 例如一个实体为 50 * 40 的矩形，若要将 同样一张50 * 40的图片绘制出来，并使图片中心与实体中心重合，Vector应为(-25,-20)<br>
 * <br>
 * 本类中的width和height表示没有进行缩放(View.scale=1.0)的情况下,实体的图片应占的大小<br>
 */
public class EntityNode extends PaintNode {

	private Vector vector;

	private double width, height;

	public EntityNode(Entity paint) {
		super(paint);
	}

	@Override
	public Entity getPaint() {
		return (Entity) super.getPaint();
	}

	public Vector getVector() {
		return vector;
	}

	public EntityNode setVector(Vector vector) {
		this.vector = vector;
		return this;
	}

	public double getWidth() {
		return width;
	}

	public EntityNode setWidth(double width) {
		this.width = width;
		return this;
	}

	public double getHeight() {
		return height;
	}

	public EntityNode setHeight(double height) {
		this.height = height;
		return this;
	}

}
