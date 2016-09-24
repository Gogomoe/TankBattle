package tankbattle.core.position;

/**
 * 本类是{@link Positionable}的默认实现方式<br>
 * 
 * @see Positionable
 * @author gogo
 *
 */
public class Positioner implements Positionable {

	private Point position = new Point();
	private Direction towards = Direction.SOUTH;
	private int layer = 0;

	@Override
	public Positionable positioner() {
		return this;
	}

	@Override
	public Point position() {
		return position;
	}

	@Override
	public Positionable setPosition(Point p) {
		if (p != null) {
			this.position = p;
		}
		return this;
	}

	@Override
	public Direction towards() {
		return towards;
	}

	@Override
	public Positionable setTowards(Direction d) {
		if (towards != null) {
			this.towards = d;
		}
		return this;
	}

	@Override
	public int layer() {
		return this.layer;
	}

	@Override
	public Positionable setLayer(int layer) {
		this.layer = layer;
		return this;
	}

	@Override
	public double getX() {
		return position.getX();
	}

	@Override
	public double getY() {
		return position.getY();
	}

}
