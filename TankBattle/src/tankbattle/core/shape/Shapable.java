package tankbattle.core.shape;

import tankbattle.core.TankBattle;
import tankbattle.core.others.Extrable;
import tankbattle.core.position.Positionable;

/**
 * 实现本接口的类具有形状<br>
 * 
 * @author Gogo
 *
 */
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
