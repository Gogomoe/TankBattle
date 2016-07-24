package tankbattle.core.shape;

import tankbattle.core.Extrable;
import tankbattle.core.TankBattle;
import tankbattle.core.position.Positionable;

public interface Shapable extends Positionable, Extrable {

	default public Shape shape() {
		return TankBattle.getGame().getProcess().send(new ShapePropertyEvent(this)).getShape();
	}

	default public Shapable setShape(Shape shape) {
		TankBattle.getGame().getProcess()
				.send(new ShapePropertyEvent(this).setShape(shape).setCode(ShapePropertyEvent.SET_SHAPE));
		return this;
	}

	default public VectorShape vshape() {
		return shape().toVectorShape(position().toVector());
	}

}
