package tankbattle.core.position;

import tankbattle.core.event.Event;

/**
 * 处理位置属性的事件<br>
 * 
 * @author Gogo
 *
 */
public class PositionPropertyEvent extends Event {

	final public static int GET_POSTION = 0;
	final public static int SET_POSTION = Integer.valueOf("001", 2);
	final public static int GET_TOWARDS = 0;
	final public static int SET_TOWARDS = Integer.valueOf("010", 2);
	final public static int GET_LAYER = 0;
	final public static int SET_LAYER = Integer.valueOf("100", 2);

	private Positionable positionable;

	private Point position;

	private Direction towards;

	private int layer;

	public PositionPropertyEvent(Positionable positionable) {
		super();
		this.positionable = positionable;
	}

	public PositionPropertyEvent(Positionable positionable, int code) {
		super();
		this.positionable = positionable;
		this.code = code;
	}

	public Point getPosition() {
		return position;
	}

	public PositionPropertyEvent setPosition(Point position) {
		this.position = position;
		return this;
	}

	public Direction getTowards() {
		return towards;
	}

	public PositionPropertyEvent setTowards(Direction towards) {
		this.towards = towards;
		return this;
	}

	public int getLayer() {
		return layer;
	}

	public PositionPropertyEvent setLayer(int layer) {
		this.layer = layer;
		return this;
	}

	public Positionable getPositionable() {
		return positionable;
	}

	public PositionPropertyEvent setPositionable(Positionable positionable) {
		this.positionable = positionable;
		return this;
	}

}
