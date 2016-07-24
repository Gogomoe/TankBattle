package tankbattle.core.shape;

import tankbattle.core.event.Event;

public class ShapePropertyEvent extends Event {

	final public static int GET_SHAPE = 0;
	final public static int SET_SHAPE = 1;

	private Shapable shaper;

	private Shape shape;

	public ShapePropertyEvent(Shapable shaper) {
		super();
		this.shaper = shaper;
	}

	public ShapePropertyEvent(Shapable shaper, int code) {
		super(code);
		this.shaper = shaper;
	}

	public Shapable getShaper() {
		return shaper;
	}

	public ShapePropertyEvent setShaper(Shapable shaper) {
		this.shaper = shaper;
		return this;
	}

	public Shape getShape() {
		return shape;
	}

	public ShapePropertyEvent setShape(Shape shape) {
		this.shape = shape;
		return this;
	}

}
