package tankbattle.core.shape;

import tankbattle.core.position.Positionable;
/**
 *  * 本类是{@link Shapable}的默认实现方式<br>
 * 
 * @see Shapable
 *
 * @author Gogo
 *
 */
public class Shaper implements Shapable {

	public Shaper(Positionable positioner) {
		if(positioner == null){
			throw new NullPointerException("Shaper 中的Positioner 为null");
		}
		Positioner = positioner;
	}

	private final Positionable Positioner;
	
	private Shape shape;
	
	@Override
	public Shapable shaper() {
		return this;
	}

	@Override
	public Positionable positioner() {
		return Positioner;
	}

	@Override
	public Shape shape() {
		return shape;
	}

	@Override
	public Shapable setShape(Shape shape) {
		if(shape != null){
			this.shape = shape;
		}
		return this;
	}

}
