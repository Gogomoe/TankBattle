package tankbattle.core.shape;

import tankbattle.core.event.Listener;

/**
 * 形状属性的监听器<br>
 * 
 * @author Gogo
 *
 */
public class ShapePropertyListener implements Listener<ShapePropertyEvent> {

	final public static String LID = "TankBattle:ShapePropertyListener";

	final public static String KEY_SHAPE = "Shapable:Shape";

	@Override
	public void listen(ShapePropertyEvent event) {
		if (event.canceled() || event.executed() || event.getShaper() == null) {
			return;
		}
		Shapable shaper = event.getShaper();
		// 形状
		if ((event.code() & ShapePropertyEvent.SET_SHAPE) == 0) {
			event.setShape(shaper.getObj(KEY_SHAPE, Shape.class));
		} else {
			shaper.put(KEY_SHAPE, event.getShape());
		}
		event.setExecuted(true);
	}

}
