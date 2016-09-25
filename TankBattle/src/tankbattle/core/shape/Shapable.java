package tankbattle.core.shape;

import tankbattle.core.others.Extrable;
import tankbattle.core.position.Positionable;

/**
 * 实现本接口的类具有形状<br>
 * <br>
 * 本类具有默认实现方式{@link Shaper}，实现本类可以参见{@link Extrable}的实现方式<br>
 * 
 * @see Shaper
 * @author Gogo
 *
 */
public interface Shapable {

	public Shapable shaper();

	public Positionable positioner();

	default public Shape shape() {
		return shaper().shape();
	}

	default public Shapable setShape(Shape shape) {
		return shaper().setShape(shape);
	}

	default public VectorShape<?> vshape() {
		return shape().toVectorShape(positioner().position().toVector());
	}

}
