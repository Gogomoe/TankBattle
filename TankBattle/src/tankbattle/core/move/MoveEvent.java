package tankbattle.core.move;

import tankbattle.core.event.Event;
import tankbattle.core.position.Vector;

public class MoveEvent extends Event {

	private Movable mover;

	private Vector vector;

	public MoveEvent(Movable mover) {
		super();
		this.mover = mover;
		this.vector = new Vector();
	}

	public Movable getMover() {
		return mover;
	}

	public MoveEvent setMover(Movable mover) {
		this.mover = mover;
		return this;
	}

	public Vector getVector() {
		return vector;
	}

	public MoveEvent setVector(Vector vector) {
		this.vector = vector;
		return this;
	}

}
