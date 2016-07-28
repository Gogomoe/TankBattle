package tankbattle.core.view;

import tankbattle.core.entity.Entity;
import tankbattle.core.position.Vector;

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

	public PaintNode setWidth(double width) {
		this.width = width;
		return this;
	}

	public double getHeight() {
		return height;
	}

	public PaintNode setHeight(double height) {
		this.height = height;
		return this;
	}

}
